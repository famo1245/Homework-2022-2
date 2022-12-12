#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[]) {	// 명령행 인자로 파일명 받을 것임
	int fd;
	caddr_t addr;
	struct stat statbuf;

	if (argc != 2) {	// 명령행 인자로 파일명을 안 쓴 경우
		fprintf(stderr, "Usage: %s filename\n", argv[0]);
		exit(1);
	}

	if (stat(argv[1], &statbuf) == -1) {	// 파일의 크기를 알기 위해 stat함수 사용
						// -1일 때 오류 예외처리
		perror("stat");
		exit(1);
	}

	if ((fd = open(argv[1], O_RDWR)) == -1) {	// 명령행 인자로 지정한 파일을 연다
		perror("open");
		exit(1);
	}

	addr = mmap(NULL, statbuf.st_size, PROT_READ | PROT_WRITE,
			MAP_SHARED, fd, (off_t)0);	// 열린 파일의 내용을 mmap 함수로
							// 메모리 매핑
							// PROT_READ|PROT_WRITE 읽기,쓰기
							// 가능하도록 지정
							// 다른 프로세스와 공유하도록
							// MAP_SHARED 플래그 지정

	if (addr == MAP_FAILED) {	// 매핑 실패시 MAP_FAILED가 리턴되므로 예외처리
		perror("mmap");
		exit(1);
	}

	close(fd);

	printf("%s", addr);
}
