/*
buffer의 size에 따라 파일 입출력 실행속도 비교
*/
//#define SIZE 256	//SIZE라는 매크로 정의
//#define SIZE 128
#define SIZE 1024
#include <stdio.h>
#include <unistd.h>	//read(), write()가 있는 헤더파일
#include <fcntl.h>	//open(), close()가 있는 헤더파일
#include <stdlib.h>

int main(void) {
	int fdin, fdout, n;	//fdin은 dummy 파일의 파일 기술자,
       				//fdout은 copy파일의 파일 기술자
	char buf[SIZE];

	fdin = open("dummy", O_RDONLY);	//dummy파일을 읽기 전용으로 연다
	if(fdin == -1) {	//fdin의 값이 -1이면 오류, 읽어오기 실패
		perror("Open dummy");	//오류 이유 출력
		exit(1);
	}

	fdout = open("copy", O_CREAT | O_WRONLY | O_TRUNC, 0644);	//copy파일을 없으면 생성하고, 쓰기 옵션으로
       								        //만일 파일이 있는 경우 내용을 지우고
									//파일 길이를 0으로 변경, 권한에 0644지정
	if(fdout == -1) {	//fdout 값이 -1이면 오류, 읽어오기 실패
		perror("Open copy");	//오류 이유 출력
		exit(1);
	}

	while((n==read(fdin, buf, SIZE)) > 0) {	//read()함수를 이용해서, fdin에서 SIZE크기만큼 읽어와서 buf에 저장
	       			                //결과값으로 읽어온 크기 값 반환
		if(write(fdout, buf, n) != n)	//write()함수를 이용해서, buf에서 n 크기만큼 읽어
		       				//fdout이 가리키는 파일에 씀, 결과 값으로 쓴 크기만큼 반환
			perror("Write");	//읽어온 크기만큼 쓰지 않았으면 error발생
	}

	if(n == -1)	//read()함수 에러
		perror("Read");

	close(fdin);
	close(fdout);
}
