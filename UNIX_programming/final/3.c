#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main() {
	int shmid, i, status;
	char *shmaddr, *shmaddr2;
	pid_t pid;
	char msg[BUFSIZ];
	char buf[BUFSIZ];
	
	// key를 IPC_PRIVATE로 지정하여 parent-child간 사용
	// 공유메모리 크기를 20바이트로 생성
	shmid = shmget(IPC_PRIVATE, 20, IPC_CREAT|0644);
	if (shmid == -1) {	// shmget 함수 에러 처리
		perror("shmget");
		exit(1);
	}

	switch (pid = fork()) {
		case -1:	// error
			perror("fork");
			exit(1);
			break;

		case 0:	//child
			// 자식 프로세스에서 공유 메모리 연결
			shmaddr = (char *)shmat(shmid, (char *)NULL, 0);
			printf("=Child %d begins\n", (int)getpid());
			// 보낼 문자열 생성
			sprintf(msg, "Message from %d\n", (int)getpid());
			// 공유 메모리에 보낼 문자열 복사
			strcpy(shmaddr, msg);
			// 공유 메모리 해제
			shmdt((char *)shmaddr);
			printf("=Child %d ends\n", (int)getpid());
			exit(0);
			break;

		default:	// parent
			waitpid(pid, &status, 0);	// 자식의 종료를 기다림
			// 부모 프로세스에서 공유 메모리 연결
			shmaddr2 = (char *)shmat(shmid, (char *)NULL, 0);
			printf("=Parent %d begins\n", (int)getpid());
			printf("%d gets ", (int)getpid());
			// 공유 메모리에서 문자열 복사해옴
			strcpy(buf, shmaddr2);
			printf("%s", buf);
			// 공유 메모리 해제
			shmdt((char *)shmaddr2);
			// 공유 메모리를 제거
			shmctl(shmid, IPC_RMID, (struct shmid_ds *)NULL);
			printf("=Parent %d ends\n", (int)getpid());
			break;
	}
}

