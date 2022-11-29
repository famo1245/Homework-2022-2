#include <sys/mman.h>
#include <signal.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>

void handler(int dummy) {
	;
}

int main() {
	pid_t pid;
	key_t key;
	int shmid;
	void *shmaddr;
	char buf[1024];
	sigset_t mask;

	printf("%d\n", getpid());

	key = ftok("shmfile", 1);
	shmid = shmget(key, 1024, IPC_CREAT|0666);

	sigfillset(&mask);
	sigdelset(&mask, SIGUSR1);
	signal(SIGUSR1, handler);

	printf("Listener wait for Talker\n");
	sigsuspend(&mask);

	printf("Listener start=====\n");
	shmaddr = shmat(shmid, NULL, 0);
	strcpy(buf, shmaddr);
	printf("Listener received : %s\n", buf);

	strcpy(shmaddr, "Have a nice day\n");
	//sleep(3);	//client가 ipcs를 실행할 시간을 줌, 그냥 여기서 해라
	printf("Before listener detach\n");
	system("ipcs -m");
	shmdt(shmaddr);
	printf("After listener detach\n");
	system("ipcs -m");
}
