#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) //argc에는 명령행 인자의 개수, argv 에는 명령행 인자가 담김
{
	int i, sum = 0;

	for(i = 1; i < argc; i++)
		sum += atoi(argv[i]);	//명령행 인자는 문자열로 전달되므로 atoi() 함수를 통해 정수로 변환

	printf("%d\n", sum);
	return 0;

}
