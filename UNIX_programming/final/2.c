#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main() {
	int fd[2];
	pid_t pid;
	int status;

	if (pipe(fd) == -1) {
		perror("pipe");	// pipe 함수 에러 처리
		exit(1);
	}

	switch (pid = fork()) {
		case -1:	// fork 함수 에러시
			perror("fork");
			exit(1);
			break;

		case 0:	//child
			close(fd[0]);
			if (fd[1] != 1) {	// 파이프의 출력 부분을 표준 출력으로 복사
				dup2(fd[1], 1);
				close(fd[1]);
			}

			execlp("ls", "ls", "-l", (char *)NULL);	// ls -l 명령 호출
			// 자식 프로세스의 ls -l 명령이 파이프를 통해 출력
			exit(0);
			break;

		default:	//parent
			waitpid(pid, &status, 0);
			close(fd[1]);
			if (fd[0] != 0) {	// 파이프의 입력 부분을 표준 입력으로 복사
				dup2(fd[0], 0);
				close(fd[0]);
			}
			
			// 자식 프로세스의 ls -l 명령의 결과를 파이프를 통해 입력받음
			execlp("grep", "grep", "c", (char *)NULL);	// grep c 명령 호출
			exit(0);
			break;
	}
}

