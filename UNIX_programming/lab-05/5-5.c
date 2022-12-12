#include <sys/wait.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main(void) {
	pid_t pid;
	int pd[2];
	char str[] = "Parent sends Pipe test";
	char buf[BUFSIZ];
	int len, status;

	if (pipe(pd) == -1) {	// pipe함수 에러 처리
		perror("pipe");
		exit(1);
	}

	switch (pid = fork()) {
		case -1:	//fork함수 에러처리
			perror("fork");
			exit(1);
			break;

		case 0:	//child
			close(pd[1]);
			len = read(pd[0], buf, BUFSIZ);	// child에서 읽어옴
			
			if (len == -1) {	//read함수 에러 처리
				perror("read");
				exit(1);
			}

			// 출력
			printf("Child: %d reads %s from the pipe.\n",(int)getpid(), buf);
			close(pd[0]);
			exit(1);
			break;

		default:	//parent
			close(pd[0]);
			
			if (write(pd[1], str, strlen(str)) == -1) { // parent에서 보냄
				perror("write");	// write함수 에러 처리
				exit(1);
			}
			
			// 보낸 문자열 출력
			printf("Parent: %d writes %s to the pipe.\n", (int)getpid(), str);
			waitpid(pid, &status, 0);
			break;
		}

	return 0;
}
