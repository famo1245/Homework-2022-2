#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void sort(char *country[], int size) {	//bubble sort 이용
	for(int i=0; i<size-1; i++) {
		for(int j=0; j<size-1; j++) {
			if(strcmp(country[j+1], country[j]) < 0) {
				char *temp = country[j+1];
				country[j+1] = country[j];
				country[j] = temp;
			}
		}
	}
}

int main(int argc, char *argv[]) {
	char *country[BUFSIZ];
	int size = 0;
	
	if(argc == 1) {	// 명령행 인자를 입력 안했을 때 예외 처리
		fprintf(stderr, "Usage: %s filename\n", argv[0]);
		exit(1);
	}
	
	for(int i=1; i<argc; i++) {	// 명령행 인자를 배열에 저장
		country[size] = argv[i];
		size++;
	}
	
	printf("Queue Order:\n");
	for(int i=0; i<size; i++) { //queue order
		printf("%s ", country[i]);
	}
	
	printf("\nStack Order:\n");
	for(int i=size-1; i > -1; i--) { //statck order
		printf("%s ", country[i]);
	}
	
	printf("\nAlphabetical Order:\n");
	sort(country, size);	// sort country
	for(int i=0; i < size; i++) {	// alphabetical order
		printf("%s ", country[i]);
	}
	
	printf("\nReverse Alphabetical Order:\n");
	for(int i=size-1; i > -1; i--) { //reverse alphabetical order
		printf("%s ", country[i]);
	}
	printf("\n");
}
