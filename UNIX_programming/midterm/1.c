/*
명령행 인자로 숫자(실수)를 받아
합을 출력하는 프로그램
*/
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {	//argc에는 명령행 인자의 개수, argv에는 명령과 각 인자를 담고있다
	double sum = 0.0;	// 출력의 형식이 실수이므로 double형으로 선언

	for(int i=1; i<argc; i++)	//argv[0]에는 명령이 담겨있으므로, 인자를 가져오려면 index 1부터 접근해야함
		sum += atof(argv[i]);	//argv배열에 명령행 인자가 문자열로 전달되므로 atof() 함수를 통해 실수로 변환
	printf("%f\n", sum);	//결과값 출력
	return 0;
}
