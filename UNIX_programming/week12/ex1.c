#include <stdio.h>
#include <stdlib.h>

#define IN 1	//inside a word
#define OUT 0	//outside a word

/* count lines, words, and characters in file that get name from arguments */

int main(int argc, char *argv[]) {
	int c, nl, nw, nc, state;
	FILE *fp;

	if((fp = fopen(argv[1], "r")) == NULL) {
		perror("open");
		exit(1);
	}

	state = OUT;
	nl = nw = nc = 0;
	while((c = fgetc(fp)) != EOF) {
		++nc;

		if(c == '\n')
			++nl;
		if(c == ' ' || c == '\n' || c == '\t')
			state = OUT;
		else if(state == OUT) {
			state = IN;
			++nw;
		}
	}

	printf("%d %d %d\n", nl, nw, nc);
}
