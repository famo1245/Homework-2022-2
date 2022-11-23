/*
Linux 터미널에서 date명령과 동일한 일을 하는 프로그램
*/
#include <time.h>	//time()함수, localtime()함수 관련 헤더
#include <stdio.h>

int main(void) {
	struct tm *tm;	//tm구조체
	time_t timep;	//time()함수의 반환형
	char *printDay[7] = {"(일)", "(월)", "(화)",
	       	"(수)", "(목)", "(금)", "(토)"};	//tm_wday는 int형,
       						//날짜를 출력하기 위한 배열 생성

	time(&timep);	//timep에 time()함수를 이용하여 시간 정보 저장
	tm = localtime(&timep);	//timep를 인자로 받아 tm구조체에
				//지역 시간대를 기준으로 시간을 처리하여 반환
	printf("%d. %d. %d. %s %d:%d:%d %s\n",1900+tm->tm_year,
			1+tm->tm_mon, tm->tm_mday, printDay[tm->tm_wday] , tm->tm_hour,
			tm->tm_min, tm->tm_sec,tm->tm_zone);	//각각 연도(1900을 더함),
       					//월(1을 더함), 일, 요일, 시, 분, 초, 지역 시간대를 출력

	return 0;
}
