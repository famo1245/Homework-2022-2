#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main() {	// 이름 있는 파이프에 문자열을 쓰는 서버 프로그램
	int pd, n;
	char msg[] = "Server sends Pipe test";
	
	printf("5-6 SERVER =====\n");	// 프로그램 이름 출력

	if (mkfifo("./MYFIFO", 0666) == -1) {	//mkfifo 함수를 통해 MYFIFO라는 FIFO파일을 생성
		perror("mkfifo");	//mkfifo 함수 실패시 에러 처리
		exit(1);
	}

	if ((pd = open("./MYFIFO", O_WRONLY)) == -1) {	//MYFIFO파일을 open 함수로 연다
		perror("open");	//open 함수 실패시 에러 처리
		exit(1);
	}

	printf("To Client: %s\n", msg);	//클라이언트로 보낼 문자열 출력

	n = write(pd, msg, strlen(msg)+1);	// MYFIFO파일에 보낼 문자열을 write
	if (n == -1) {	//write함수 실패시 에러처리
		perror("write");
		exit(1);
	}
	close(pd);
}
