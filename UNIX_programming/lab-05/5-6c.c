#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main() {
	int pd, n;
	char remsg[80];	// 읽어올 문자열을 저장하는 곳

	if ((pd = open("./MYFIFO", O_RDONLY)) == -1) {	//MYFIFO파일을 open
		perror("open");	// open 실패시 에러 처리
		exit(1);
	}

	printf("5-6 Client======\n");	// 클라이언트 프로그램임을 나타냄
	write(1, "From Server: ", 13);	// 출력

	while (( n = read(pd, remsg, 80)) > 0) {	//이름있는 파이프로부터 문자열 read
		write(1, remsg, n);	// 읽어온 문자열 stdout에 출력
	}

	if (n == -1) {	// read함수 실패시 에러처리
		perror("read");
		exit(1);
	}

	write(1, "\n", 1);	//줄바꿈
	close(pd);
}
