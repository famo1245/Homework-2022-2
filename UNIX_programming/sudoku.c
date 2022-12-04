#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <sys/time.h>
#include <termios.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <sys/types.h>
#include <time.h>

/* 타이머 */
#define CCHAR 0
#ifdef CTIME
#undef CTIME
#endif
#define CTIME 1

/* 게임 시작, 종료 정의 */
#define GAME_START 0
#define GAME_END 1

/* 스도쿠 판 */
int sudoku_table[9][9];	// 게임에서 생성된 스도쿠 판, 정답
int sudoku_input[9][9];	// 사용자가 입력하게 될 스도쿠 판

/* 게임 종료때 마다
   날짜, 시간이저장되는 구조체 */
static struct result {	
	int *sudoku_input;
	int year;
	int month;
	int day;
	int hour;
	int min;
}temp_result;

int game = GAME_END; /*게임 상태 변수, 게임이 시작되거나 종료됨에 따라 변한다*/

int print_menu(void); /* 메뉴를 출력하는 함수 */
//int print_sudoku_table(void); /* 현재의 스도쿠판을 출력하는 함수 */
//int display_tetris_table(void); /* 현재의 테트리스판을 보여준다. 블록이 놓이고 쌓인 현재 상태를 보여줌*/
//int game_start(void); /* 게임 시작시 호출되는 함수.   game변수를 참조하여 게임을 종료하거나 시작함 . 게임 시작시 refresh()함수가 콜백함수로 설정되고 타이머를 등록함. */
//int print_result(void);/* 메뉴에서 기록출력시 호출되어 기록을 출력하는 함수*/
//int search_result(void); /*메뉴에서 기록검색시 호출되어 기러고을 검색하는 함수*/

int main(void) {
	int menu = 1;

	while(menu) {
		menu = print_menu();

		if (menu == 1) {
			game = GAME_START;
			//menu = game_start();
		}
		else if (menu == 2) {
			//print_result();
		}
		else if (menu == 3) {
			print("\t\t\t게임을 종료합니다...\n");
			sleep(1);
			exit(0);
		}
	}

	return 0;
}

/* 메뉴를 출력하는 함수 */
int print_menu(void)
{
	int menu = 0;

	while(1)
	{
		system("clear");
		printf("\n\n\t\t\t\t   Sudoku!");
		printf("\n\t\t\t============================");
		printf("\n\t\t\t\t  GAME MENU\t\n");
		printf("\n\t\t\t============================");
		printf("\n\t\t\t   1) Game Start");
		printf("\n\t\t\t   2) Show Result");
		printf("\n\t\t\t   3) QUIT");
		printf("\n\t\t\t============================");
		printf("\n\t\t\t\t\t SELECT : ");
		scanf("%d",&menu);
		
		if (menu < 1 || menu > 3) {	// 예외 처리
			printf("\t\t\t1-3 사이의 숫자를 입력해 주세요!\n");
			sleep(1);
			continue;
		}
		
		else {
			return menu;
		}
	}
	
	return 0;
}

/*
/* 게임 시작시 호출되는 함수.   game변수를 참조하여 게임을 종료하거나 시작함 . 게임 시작시 refresh()함수가 콜백함수로 설정되고 타이머를 등록함. */
/*int game_start(void)
{
	static struct sigaction sa;
	static struct itimerval timer;
	time_t ptime;
	struct tm *t;
	FILE *fp = NULL;

	if(game == GAME_START)
	{
		print_sudoku_table();

		// install timer_handler as the signal handler for SIGVTALRM.
		memset(&sa, 0, sizeof (sa));
		sa.sa_handler = &refresh;
		sigaction(SIGVTALRM, &sa, NULL);

		// Configure the timer to expire after 250 msec... 
		timer.it_value.tv_sec = 0;
		timer.it_value.tv_usec = 1;

		// ... and every 250 msec after that.
		timer.it_interval.tv_sec = 0;
		timer.it_interval.tv_usec = 1;

		// Start a virtual timer. It counts down whenever this process is executing.
		setitimer(ITIMER_VIRTUAL, &timer, NULL);

		// Do busy work.

		while(1) {
			if(game == GAME_END)
			{

				timer.it_value.tv_sec = 0;
				timer.it_value.tv_usec = 0;
				timer.it_interval.tv_sec = 0;
				timer.it_interval.tv_usec = 0;
				setitimer(ITIMER_VIRTUAL,&timer,NULL);


				// 기록 파일로 저장

				printf("\n\n Final score : %ld ", point);
				printf("\n\n Please enter your name : ");
				scanf("%s%*c", temp_result.name);
				temp_result.point = point;

				if(temp_result.point >= best_point)
					best_point = temp_result.point;


				ptime = time(NULL); // 현재 시각을 초 단위로 얻기
				t = localtime(&ptime); // 초 단위의 시간을 분리하여 구조체에 넣기

				temp_result.year = t->tm_year + 1900;
				temp_result.month = t->tm_mon + 1;
				temp_result.day = t->tm_mday;
				temp_result.hour = t->tm_hour;
				temp_result.min = t->tm_min;

				fp = fopen("result", "ab");
				fseek(fp, 1, SEEK_END);
				fwrite(&temp_result, sizeof(struct result), 1, fp);
				fclose(fp);

				x = 3, y =0;
				point = 0;

				return 1;
			}
		}
	}

  return 0;
}*/


/* 현재의 스도쿠판을 출력하는 함수 */
int print_sudoku_table(void)
{
	int i = 0, j = 0;
	
	printf("| 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │\n");
	printf("┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───\n");

	for(;i < 9; i++) {
		j = 0;
		
		for(;j < 9; j++) {
			if(sudoku_table[i][j] == 0)
				printf("│   ");
			else
				printf("│ %d ", sudoku_table[i][j]);
		}
		printf("│ %d\n",i+1);
		
		if (i == 8)	// 마지막줄 출력 양식
			printf("└───┴───┴───┴───┴───┴───┴───┴───┴───┴───\n");
		
		else
			printf("├───┼───┼───┼───┼───┼───┼───┼───┼───┼─── \n");
	}
	
	

	return 0;
}

/*메뉴에서 기록검색시 호출되어 기러고을 검색하는 함수*/
/*int search_result(void)
{
	FILE *fp = NULL;
	char name[30];
	char ch;
	int find = 0;

	fp = fopen("result", "rb");

	if(fp == NULL)
		return 0;

	system("clear");

	printf("\n\n\t\tEnter the name your to search.  : ");
	scanf("%s%*c", name);

	printf("\n\t\t\t\tText Tetris");
	printf("\n\t\t\t\t Game Stats\n\n");
	printf("\n\t\tName\t\tScore\t   Date\t\t Time");

	while(1)
	{
		fread(&temp_result, sizeof(struct result), 1, fp);
		if(!feof(fp))
		{
			if(!strcmp(temp_result.name, name))
			{
				find = 1;
				printf("\n\t========================================================");
				printf("\n\t\t%s\n\t\t\t\t%ld\t%d.%d.%d.  |  %d:%d\n", temp_result.name, temp_result.point, temp_result.year, temp_result.month, temp_result.day, temp_result.hour, temp_result.min);
			}
		}
		else
		{
			break;
		}
	}

	if(find == 0)
		printf("\n\n\n\t\tThis name is not found.");

	printf("\n\n\n\t\tBack to the game menu : M");
	while(1)
	{
		ch = getch();
		if(ch == 77 || ch == 109)
			break;
	}

	return 0;
}*/

/* 메뉴에서 기록출력시 호출되어 기록을 출력하는 함수*/
/*int print_result(void)
{
	FILE *fp = NULL;
	char ch = 1 ;

	fp = fopen("result", "rb");

	if(fp == NULL)
		return 0;

	system("clear");

	printf("\n\t\t\t\tText Tetris");
	printf("\n\t\t\t\t Game Stats\n\n");
	printf("\n\t\tName\t\tScore\t   Date\t\t Time");

	while(1)
	{
			fread(&temp_result, sizeof(struct result), 1, fp);
			if(!feof(fp))
			{
				printf("\n\t========================================================");
				printf("\n\t\t%s\n\t\t\t\t%ld\t %d.%d.%d.  |  %d:%d\n", temp_result.name, temp_result.point, temp_result.year, temp_result.month, temp_result.day, temp_result.hour, temp_result.min);
			}
			else
			{
				break;
			}
	}

	fclose(fp);

	printf("\n\n\tBack to the game menu : M");
	while(1)
	{
		ch = getch();
		if(ch == 77 || ch == 109)
			break;
	}
	return 0;

}*/