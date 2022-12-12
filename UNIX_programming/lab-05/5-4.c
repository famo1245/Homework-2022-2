#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(void) {
	int pd[2];
	char str[] = "Pipe test";
	char buf[BUFSIZ];
	int len, status;

	if (pipe(pd) == -1) {	//pipe()에 대한 에러 처리
		perror("pipe");
		exit(1);
	}

	if ((write(pd[1], str, strlen(str))) == -1) {	// 파이프에 str문자열 쓰기
		perror("write");	// write 에러 처리
		exit(1);
	}

	printf("%d writes %s to the pipe.\n", (int)getpid(), str);
	
	len = read(pd[0], buf, BUFSIZ);	// 파이프에서 읽어오기

	if (len == -1) {	//read()에 대한 에러 처리
		perror("read");
		exit(1);
	}

	printf("%d reads %s from the pipe.\n", (int)getpid(), buf); // 파이프로부터 읽은 문자열 출력

	close(pd[0]);
	close(pd[1]);

	return 0;
}
