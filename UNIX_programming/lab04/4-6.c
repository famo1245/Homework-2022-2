/*
4-5.c에서 parent process와
child process의 역할을 바꾸기
구현이 어려운 이유 마지막 주석
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
        int n = 1;


        if ((pid = fork()) < 0) {       //fork failed
                perror("fork");
                exit(1);
        }

        if (pid > 0) {         //parent process
                if((wfp = fopen("4-6.txt", "w")) == NULL) {    //file open failed
                        perror("fopen: 4-6.txt");
                        exit(1);
                }

                printf("입력(^D 종료) >> ");

                while(1) {      // get text from stdin
                        fgets(input, sizeof(input), stdin);
                        for(int i=0 ; i < sizeof(input) - 1; i++) {
                                if (input[i] == '^' && input[i+1] == 'D') {     // write to 4-5.txt
                                        input[i] = '\n';
					input[i+1] = '\0';
                                        fprintf(wfp, "%s", input);
					n = 0;
                                        break;
                                }
                        }

			if(n == 0)
				break;

                        fprintf(wfp, "%s", input);
             	}

		fclose(wfp);

		while(waitpid(pid, &status, 0) == 0) {
			sleep(3);
		}
		
		printf("End of program\n");
        }

        else {          //child process
                sleep(30);

                if ((rfp = fopen("4-6.txt", "r")) == NULL) {    //file open failed
                        perror("fopen: 4-6.txt");
                        exit(1);
                }

                printf("\n4-6.txt :\n");

                while(fgets(buf, sizeof(buf), rfp) != NULL) {   //read from 4-5.txt and print to stdout
                        printf("%s", buf);
                }

                printf("End of file\n");
                fclose(rfp);
		exit(3);
        }
}

// wait()함수나 sleep()함수 만으로는 child process에서
// parent process가 종료할 때까지 기다릴 수 있는 방법 없음
// process 간 공유 메모리나, IPC 기법이 필요함

