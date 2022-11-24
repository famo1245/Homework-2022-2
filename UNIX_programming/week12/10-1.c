#include <stdlib.h>
#include <stdio.h>

int main() {
	FILE *fp;
	int a;

	fp = popen("wc", "w");
	if(fp == NULL) {
		fprintf(stderr, "popen failed\n");
		exit(1);
	}

	for (a = 0; a < 200; a++)
		fprintf(fp, "test line\n");

	pclose(fp);
}
