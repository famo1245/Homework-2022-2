#include <signal.h>
#include <stdio.h>

int main() {
	sigset_t st;

	sigemptyset(&st);

	if(sigismember(&st, SIGINT))
		printf("SIGINT has been set.\n");
	else
		printf("SIGINT is not a member.\n");

	sigaddset(&st, SIGINT);
	sigaddset(&st, SIGQUIT);

	if (sigismember(&st, SIGINT))
		printf("SIGINT has been set.\n");

	printf("**Bit Pattern: %lx\n", st.__val[0]);

	sigdelset(&st, SIGQUIT); // delete sigquit
	printf("**Bit Pattern: %lx\n", st.__val[0]);
}
