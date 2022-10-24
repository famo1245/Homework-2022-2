#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main() {
	printf("PID :%d\n", (int)getpid());
	printf("PPID : %d\n", (int)getppid());
	system("ps");
}
