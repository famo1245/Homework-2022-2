#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main() {
	int fd,n;
	char buf[10];

	fd = open("linux.txt", O_RDONLY);
	if(fd==-1) {
		perror("Open");
		exit(1);
	}
	
	printf("buf=");

	while(n=read(fd, buf, 10)) {
		if(n == -1) {
			perror("Read");
			exit(1);
		}

		buf[n]='\0';
		printf("%s",buf);
	}

	printf("\n");
	
	close(fd);
}
