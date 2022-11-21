#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <stdio.h>

int main() {
	printf("Before SIGCONT Signal to parent.\n");
	kill(getpid(), SIGCONT);

	printf("Before SIGQUIT Signal to me.\n");
	kill(getpid(), SIGQUIT);

	printf("After SIGQUIT Signal.\n");
}
