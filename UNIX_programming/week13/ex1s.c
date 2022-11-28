/*
server에서 입력을 받아 client에서 출력
*/
#include <sys/stat.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main() {
	int pd, n, fd[2], status;
	pid_t pid;
	char msg[80];

	printf("Server ====\n");
	
	if (pipe(fd) == -1) {
		perror("pipe");
		exit(1);
	}

	if ((pid = fork()) == -1) {
		perror("fork");
		exit(1);
	}

	if(pid > 0) {
		close(fd[0]);
		printf("Input: ");
		fgets(msg, sizeof(msg), stdin);
		write(fd[1], msg, sizeof(msg));
		close(fd[1]);
		waitpid(pid, &status, 0);
	}
	
	else {
		sleep(5);
		close(fd[1]);
		if ((pd = open("./YOUNG-FIFO", O_WRONLY)) == -1) {
			perror("open");
			exit(1);
		}
		
		read(fd[0], msg, 79);
		printf("To client: %s\n", msg);

		n = write(pd, msg, strlen(msg)+1);
		if (n == -1) {
			perror("write");
			exit(1);
		}
		close(fd[0]);
		close(pd);
	}
}
