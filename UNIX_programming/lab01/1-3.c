#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void)
{
	char *str;

	//str = malloc(sizeof(char) * 20);    //malloc함수를 이용하여 char크기의 20칸 배열 메모리공간 할당

	strcpy(str, "Hello");	//strcpy()로 문자열 "Hello"를 사
	printf("%s\n", str);

	strcpy(str, "Good morning");	//strcpy()로 문자열  "Good morning"을 str에 복사
	printf("%s\n", str);

	free(str);	//str에 할당한 메모리 반환
	return 0;
}
