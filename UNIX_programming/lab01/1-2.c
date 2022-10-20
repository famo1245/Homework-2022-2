#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main(void)
{
	if (access("linux.txt", F_OK) == -1) {//access함수로 linux.text
					      //파일 존재여부 확인
		perror("linux.txt");	//perror() 함수로 오류메시지 출력
		exit(1);		//오류의 결과로 프로그램 종료
	}
	return 0;
}
