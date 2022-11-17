/*
parent process에서 3개의 child process를 fork()하여
각 child process의 pid와 ppid를 출력하는 프로그램
*/
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

#define FORKSIZE 3

int main(void) {
	pid_t pid[FORKSIZE];
	int idx = 0;

	while(idx < FORKSIZE) {
		if((pid[idx] = fork()) < 0) {
			perror("fork");
			exit(1);
		}
		if(pid[idx] == 0) {	//child process
			printf("Child %d --> my pid is %d and ppid is %d\n", idx+1, (int)getpid(), (int)getppid());
			exit(2);
		}
		else	// parent process
			sleep(1);	//sleep for 1sec
		idx++;
	}

	printf("End of fork\n");

	return 0;
}
