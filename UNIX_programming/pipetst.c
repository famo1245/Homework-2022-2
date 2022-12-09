#include <errno.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/wait.h>
#include <sys/sem.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

union semun {
	int val;
	struct semid_ds *buf;
	unsigned short *array;
};

int initsem(key_t semkey) {
	union semun semunarg;
	int status = 0, semid;

	semid = semget(semkey, 1, IPC_CREAT | IPC_EXCL | 0600);
	if (semid == -1) {
		if (errno == EEXIST)
			semid = semget(semkey, 1, 0);
	}
	else {
		semunarg.val = 1;
		status = semctl(semid, 0, SETVAL, semunarg);
	}

	if (semid == -1 || status == -1) {
		perror("initsem");
		return (-1);
	}

	return semid;
}

int semlock(int semid) {
	struct sembuf buf;

	buf.sem_num = 0;
	buf.sem_op = -1;
	buf.sem_flg = SEM_UNDO;
	if (semop(semid, &buf, 1) == -1) {
		perror("semlock failed");
		exit(1);
	}
	return 0;
}

int semunlock(int semid) {
	struct sembuf buf;

	buf.sem_num = 0;
	buf.sem_op = 1;
	buf.sem_flg = SEM_UNDO;
	if (semop(semid, &buf, 1) == -1) {
		perror("semunlock failed");
		exit(1);
	}
	return 0;
}

/*void semhandle() {
	int semid;
	pid_t pid = getpid();

	if ((semid = initsem(1)) < 0)	//IPC_PRIVATE는 critical section이
	       				//지켜지지 않음
					//ftok의 key가 들어오는 것이 낫다
		exit(1);

	semlock(semid);
	printf("Lock : Process %d\n", (int)pid);
	printf("** Lock Mode : Critical Section\n");
	sleep(1);
	printf("Unlock : Process %d\n", (int)pid);
	semunlock(semid);

	exit(0);
}*/

int main() {
	int fd1[2], fd2[2];
	pid_t pid;
	char buf[3];
	int len, status, count;
	char input;

	if (pipe(fd1) == -1) {
		perror("pipe");
		exit(1);
	}

	if (pipe(fd2) == -1) {
		perror("pipe");
		exit(1);
	}

	switch(pid = fork()) {
		case -1:
			perror("fork");
			exit(1);
			break;

		case 0:	//child
			count = 0;
			close(fd1[1]);
			close(fd2[0]);
			while(1) {
				len = read(fd1[0], buf, 3);
				if (buf[0] == '0') {
					exit(0);
					break;
				}
				printf("child: %d %d %d\n", buf[0], buf[1], buf[3]);
				
				if (count == 10)
					buf[0] = 'q';
				else
					buf[0] = 'a';

				write(fd2[1], buf, 1);
				count++;
			}
			break;

		default:	//parent
			count = 1;
			close(fd1[0]);
			close(fd2[1]);
			while(1) {
				buf[0] = count;
				buf[1] = count;
				buf[2] = count;
				count++;
				write(fd1[1], buf, 3);
				len = read(fd2[0], &input, 1);
                                if (input == 'q') {
                                        buf[0] = '0';
                                        buf[1] = 1;
                                        buf[2] = 3;
                                        write(fd1[1], buf, 3);
                                        break;
                                }

                                printf("parent: %c\n", input);

			}

			waitpid(pid, &status, 0);
			break;
	}
}
				


