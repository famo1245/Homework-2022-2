#include <unistd.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

void handler(int signo) {	//signal handler 정의
	psignal(signo, "Received Signal:");
	sleep(5);
	printf("In Signal Handler, After Sleep\n");
}

int main(void) {
	struct sigaction act;

	sigemptyset(&act.sa_mask);	// 시그널 집합 비우기
	act.sa_flags = 0;
	act.sa_handler = handler;	// 시그널 핸들러 등록
	if (sigaction(SIGINT, &act, (struct sigaction *)NULL) < 0) {	//SIGINT 시그널을 받을 경우 동작을 지정한다
		perror("sigaction");
		exit(1);
	}

	while (1) {
		fprintf(stderr, "Input SIGINT: ");
		pause();
		fprintf(stderr, "After Signal Handler\n");
	}

	return 0;
}
