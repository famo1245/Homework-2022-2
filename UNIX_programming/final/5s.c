#include <sys/socket.h>
#include <sys/un.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define PORTNUM 9000

int main() {
	char buf[256];
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

	while(1) {
		// 클라이언트로부터 연결 요청 수락
		if ((ns = accept(sd, (struct sockaddr *)&cli, &clientlen)) == -1) {
		perror("accept");
		exit(1);
		}
		
		// 클라이언트로부터 문자열 수신
		if (recv(ns, buf, sizeof(buf), 0) == -1) {
		perror("recv");
		exit(1);
		}

		int j = strlen(buf) - 1;
		for(int i = 0; i < strlen(buf); i++) {	// 문자열 뒤집기
				msg[j] = buf[i];
				j--;
		}
		msg[strlen(buf)] = '\0';	// 문자열 끝에 null문자 추가
	
		// 클라이언트로 문자열 송신
		if (send(ns, msg, strlen(msg) + 1, 0) == -1) {
			perror("send");
			exit(1);
		}
		
		// 문자열 비우기
		memset(msg, '\0', sizeof(char));
		memset(buf, '\0', sizeof(char));
	}
	// 소켓 파일 기술자 종료
	close(ns);
	close(sd);
}

