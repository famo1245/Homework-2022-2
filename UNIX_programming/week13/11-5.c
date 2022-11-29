#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main() {
	int shmid, i;
	char *shmaddr, *shmaddr2;

	shmid = shmget(IPC_PRIVATE, 20, IPC_CREAT|0644);
	if (shmid == -1) {
		perror("shmget");
		exit(1);
	}

	switch (fork()) {
		case -1:
			perror("fork");
			exit(1);
			break;

		case 0:
			shmaddr = (char *)shmat(shmid, (char *)NULL, 0);
			printf("Child process====\n");
			for (i=0; i<10; i++)
				shmaddr[i] = 'a' + i;
			printf("Before detach child\n");
			system("ipcs -m");
			shmdt((char *)shmaddr);
			printf("After detach child\n");
			system("ipcs -m");
			exit(0);
			break;

		default:
			wait(0);
			shmaddr2 = (char *)shmat(shmid, (char *)NULL, 0);
			printf("Parent process====\n");
			for (i=0; i<10; i++)
				printf("%c ", shmaddr2[i]);
			printf("\n");
			printf("Before detach parent\n");
			system("ipcs -m");
			shmdt((char *)shmaddr2);
			printf("After detach parent\n");
			system("ipcs -m");
			shmctl(shmid, IPC_RMID, (struct shmid_ds *)NULL);
			break;
	}
}
