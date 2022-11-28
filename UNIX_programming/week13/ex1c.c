/*
server에서 입력을 받아 client에서 출력
*/
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

void convertCase(char *str);

int main() {
	int pd, n;
	char inmsg[80];

	if ((pd = open("./YOUNG-FIFO", O_RDONLY)) == -1) {
		perror("open");
		exit(1);
	}

	printf("Client =====\n");
	write(1, "From Server :", 13);

	while((n = read(pd, inmsg, 80)) > 0) {
		convertCase(inmsg);
		write(1, inmsg, n);
	}

	write(1, "\n", 1);
	close(pd);
}

void convertCase(char *str) {
	for(int i = 0; i < sizeof(str); i++) {
		int interval = 'A' - 'a';
		if (str[i] >= 'a' && str[i] <= 'z')
			str[i] += interval;
		else if (str[i] >= 'A' && str[i] <= 'Z')
			str[i] -= interval;
	}
}
