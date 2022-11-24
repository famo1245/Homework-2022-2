/*
10-2를 pipe() 함수로 구현하기
*/
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main() {
	int fd[2];
	int len, status;
	char buf[256];
	pid_t pid;

	if (pipe(fd) == -1) {
		perror("pipe");
		exit(1);
	}

	switch (pid = fork()) {
		case -1:
			perror("fork");
			exit(1);
			break;

		case 0:	//child
			close(fd[0]);
			if (fd[1] != 1) {
				dup2(fd[1], 1);
				close(fd[1]);
			}

			execlp("date", "date", (char *)NULL);
			exit(1);
			break;

		default:	//parent
			close(fd[1]);
			len = read(fd[0], buf, 256);
			write(1, "Parent:", 7);
			write(1, buf, len);
			waitpid(pid, &status, 0);
			break;
	}
}
