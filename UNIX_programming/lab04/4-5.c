/*
child process에서 stdin으로 입력받아 4-5.txt 파일에 write
parent process는 child process의 종료를 기다리고
4-5.txt 파일의 내용을 read하여 stdout에 출력하는 프로그램
*/
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
        int status;
        pid_t pid;
        char buf[20];
        FILE *wfp, *rfp;
        char input[20];


        if ((pid = fork()) < 0) {       //fork failed
                perror("fork");
                exit(1);
        }

        if (pid == 0) {         //child process
                if((wfp = fopen("4-5.txt", "w")) == NULL) {    //file open failed
                        perror("fopen: 4-5.txt");
                        exit(1);
                }

                printf("입력(^D 종료) >> ");

                while(1) {	// get text from stdin
            		fgets(input, sizeof(input), stdin);
            		for(int i=0 ; i < sizeof(input) - 1; i++) {
                		if (input[i] == '^' && input[i+1] == 'D') {	// write to 4-5.txt until input ^D
					input[i] = '\n';
					input[i+1] = '\0';
					fprintf(wfp, "%s", input);
					fclose(wfp);
					exit(3);
                		}
           		}

			fprintf(wfp, "%s", input);
		}
    	}

        else {          //parent process
                while (waitpid(pid, &status, 0) == 0) {	// wait for child process end
                        sleep(3);
                }

                if ((rfp = fopen("4-5.txt", "r")) == NULL) {	//file open failed
                        perror("fopen: 4-5.txt");
                        exit(1);
                }

                printf("\n4-5.txt :\n");

                while(fgets(buf, sizeof(buf), rfp) != NULL) {	//read from 4-5.txt and print to stdout
                        printf("%s", buf);
                }

		printf("End of file\n");

                fclose(rfp);
        }
}

