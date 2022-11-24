#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

#define IN 1
#define OUT 0

/* count lines, words, and characters from file using mmap*/

int main(int argc, char *argv[]) {
	int c, nl, nw, nc, state, fd;
	caddr_t addr;
	struct stat statbuf;

	state = OUT;
	nl = nw = nc = 0;

	if (argc != 2) {
		fprintf(stderr, "Usage : %s filename\n", argv[0]);
		exit(1);
	}

	if (stat(argv[1], &statbuf) == -1) {
		perror("stat");
		exit(1);
	}

	if ((fd=open(argv[1], O_RDONLY)) == -1) {
		perror("open");
		exit(1);
	}

	addr = mmap(NULL, statbuf.st_size, PROT_READ, MAP_SHARED, fd, (off_t)0);

	if (addr == MAP_FAILED) {
		perror("mmap");
		exit(1);
	}

	close(fd);

	for(int i=0; i <statbuf.st_size; i++) {
		c = addr[i];
		++nc;

		if (c == '\n')
			++nl;

		if (c == ' ' || c == '\n' || c == '\t')
			state = OUT;
		else if (state == OUT) {
			state = IN;
			++nw;
		}
	}

	printf("cout of line: %d\ncount of word:  %d\ncount of char :	%d\n",
		       	nl, nw, nc);
}

// 메모리를 희생하지만, 크기가 큰 파일에서 처리 속도가 빠름
