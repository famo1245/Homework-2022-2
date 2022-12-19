#include <unistd.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

int count = 0;	// handler가 동작한 횟수 기록

void handler(int signo) {	//signal handler 정의
	count++;
	printf("Handler is called %d time(s)\n", count); // 동작횟수 출력
}

int main(void) {
	struct sigaction act;

	sigemptyset(&act.sa_mask);	// 시그널 집합 비우기
	act.sa_flags = 0;
	act.sa_handler = handler;	// 시그널 핸들러 등록
	
	//SIGINT 시그널을 받을 경우동작을 지정한다
	if (sigaction(SIGINT, &act, (struct sigaction *)NULL) < 0) {
		perror("sigaction");	//sigaction 실패시 예외 처리
		exit(1);
	}

	while (1) {	// 무한 루프 돌면서 출력
		printf("I'm in infinite loop\n");
		sleep(5);	//5초간 sleep
	}

	return 0;
}

