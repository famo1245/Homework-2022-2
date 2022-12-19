#include <sys/socket.h>
#include <sys/un.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define PORTNUM 9000

int main() {
	char msg[256];
	struct sockaddr_in sin, cli;
	int sd, ns, clientlen = sizeof(cli);

	// socket 함수를 통해 소켓 파일 기술자 생성
	if ((sd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
		perror("socket");	// socket 함수 에러처리
		exit(1);
	}

	memset((char *)&sin, '\0', sizeof(sin));	// 소켓 구조체 생성
	sin.sin_family = AF_INET;
	sin.sin_port = htons(PORTNUM);
	sin.sin_addr.s_addr = inet_addr("127.0.0.1");
	
	// 지정된 IP주소와 포트 번호를 소켓 파일 기술자에 결합
	if (bind(sd, (struct sockaddr *)&sin, sizeof(sin))) {
		perror("bind");	// 에러 처리
		exit(1);
	}

	// 클라이언트로부터 연결 요청 대기
	if (listen(sd, 5)) {
		perror("listen");	// 에러 처리
		exit(1);
	}
	
	// 클라이언트로부터 연결 요청 수락
		if ((ns = accept(sd, (struct sockaddr *)&cli, &clientlen)) == -1) {
		perror("accept");
		exit(1);
		}

	while(1) {
		// 사용자로부터 입력을 받음
		printf("클라이언트 종료를 원할 시 quit 또는 Quit 입력: ");
		scanf("%s", msg);
	
		// 클라이언트로 문자열 송신
		if (send(ns, msg, strlen(msg) + 1, 0) == -1) {
			perror("send");
			exit(1);
		}
		
		// break 조건 만족시 종료
		if(strcmp(msg, "quit") == 0 || strcmp(msg, "Quit") == 0) {
			printf("종료 입력!! 프로그램을 종료합니다...\n");
			break;
		}
		
		printf("프로그램을 계속 실행합니다\n");
		// 문자열 비우기
		memset(msg, '\0', sizeof(char));
	}
	// 소켓 파일 기술자 종료
	close(ns);
	close(sd);
}

