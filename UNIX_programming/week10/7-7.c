#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main() {
	pid_t pid;

	switch (pid = fork()) {
		case -1:	//fork failed
			perror("fork");
			exit(1);
			break;

		case 0:	//child process
			printf("--> Child Process\n");
			if (execlp("ls", "ls", "-l", (char *)NULL) == -1) {
				perror("execlp");
				exit(1);
			}
			exit(0);
			break;

		default:	//parent process
			printf("--> Parent Process - My PID: %d\n", (int)getpid());
			break;
	}
}
