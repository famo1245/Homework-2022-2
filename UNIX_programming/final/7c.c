#include <sys/socket.h>
#include <sys/un.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#define PORTNUM 9000

int main() {
	int sd;
	char buf[256];
	struct sockaddr_in sin;
	
	// 소켓 파일 기술자 생성
	if ((sd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
		perror("socket");
		exit(1);
	}
	
	// 소켓 구조체 생성
	memset((char *)&sin, '\0', sizeof(sin));
	sin.sin_family = AF_INET;
	sin.sin_port = htons(PORTNUM);	// 포트 번호 지정
	sin.sin_addr.s_addr = inet_addr("127.0.0.1");	// IP주소 지정
	
	// 서버에 연결요청
	if (connect(sd, (struct sockaddr *)&sin, sizeof(sin)) == -1) {
		perror("connect");	// connect 함수 실패시 에러 처리
		exit(1);
	}
	
	while(1) {
		// 서버로부터 문자열 수신
		if (recv(sd, buf, sizeof(buf), 0) == -1) {
			perror("recv");
			exit(1);
		}
		
		printf("클라이언트 실행중...\n");
		
		// break 조건 만족시 종료
		if(strcmp(buf, "quit") == 0 || strcmp(buf, "Quit") == 0) {
			printf("From Server : %s\n", buf);
			printf("클라이언트를 종료합니다...\n");
			break;
		}
	}
	
	close(sd);	// 소켓 파일 기술자 종료
}

