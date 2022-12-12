#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[]) {
	int fd, status;
	pid_t pid;
	caddr_t addr;
	struct stat statbuf;

	if (argc != 2) {	// 명령행 인자를 안 넣었을 경우 에러
		fprintf(stderr, "Usage: %s filename\n", argv[0]);
		exit(1);
	}

	if (stat(argv[1], &statbuf) == -1) {	// stat함수 실패시
		perror("stat");
		exit(1);
	}

	if ((fd = open(argv[1], O_RDWR)) == -1) {	// open함수 실패시
		perror("open");
		exit(1);
	}

	addr = mmap(NULL, statbuf.st_size, PROT_READ | PROT_WRITE,
			MAP_SHARED, fd, (off_t)0);	// 입력한 파일명으로 메모리 매핑

	if (addr == MAP_FAILED) {	// mmap함수 실패시
		perror("mmap");
		exit(1);
	}

	close(fd);
	
	switch(pid = fork()) {
		case -1:
			perror("fork");
			exit(1);
			break;

		case 0:	//child
			printf("%s", addr);	// 출력
			exit(0);
			break;

		default:	//parent
			waitpid(pid, &status, 0);
			break;
	}
}
