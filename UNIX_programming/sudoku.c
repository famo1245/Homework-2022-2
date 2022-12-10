// TODO: 게임 시작 함수
// 파일 출력 함수
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
#include <stdbool.h>
#include <sys/wait.h>

/* 타이머 
#define CCHAR 0
#ifdef CTIME
#undef CTIME
#endif
#define CTIME 1
*/


#define GAME_START 0 // 게임 시작,
#define GAME_END 1	// 종료 정의
#define N 9 // 행과 열
#define SRN 3 // square and row
#define EASY 20 // 쉬움 모드
#define HARD 30 // 어려움 모드

/* 스도쿠 판 */
int sudoku_table[9][9] = {0, };	// 게임에서 생성된 스도쿠 판, 0으로 채워짐
int sudoku_input[9][9];	// 사용자가 입력하게 될 스도쿠 판

/* 게임 종료때 마다 사용자가 입력한 스도쿠 테이블, 플레이 시간이 저장되는 구조체 */
static struct result {	
	int *user_input;
	int year;
	int month;
	int day;
	int hour;
	int min;
}temp_result;

int game = GAME_END; // 게임 상태 변수, 게임이 시작되거나 종료됨에 따라 변함
int MODE = EASY; // 게임의 난이도 변수, 사용자의 설정에 따라 변함, 기본 값은 쉬움

int print_menu(void); // 메뉴를 출력하는 함수
void selectMode(void);	// 게임 시작 전, 난이도를 설정하는 메뉴
void print_sudoku_table(void); // 현재의 스도쿠판을 출력하는 함수
int game_start(void); /* 게임 시작시 호출되는 함수.
game변수를 참조하여 게임을 종료하거나 시작함. 
게임 시작시 타이머를 등록함.
자식 프로세스에서 스도쿠를 만들고
부모 프로세스에서 사용자로 부터 숫자를 입력받음
*/
int print_result(void);	// 메뉴에서 기록검색을 선택하면 기록을 파일로 출력하는 함수
bool unUsedInBox(int rowStart, int colStart, int num);
bool unUsedInRow(int i, int num);
bool CheckIfSafe(int i, int j, int num);
bool unUsedInCol(int j, int num);
void fillBox(int row, int col);	// 한 칸에 숫자를 넣는 함수
void fillDiagonal(void);
bool fillRemaining(int i, int j);
void removeKDigits(void);
void init_sudoku_table(void);	// 스도쿠판을 생성하는 함수


int main(void) {
	int menu = 1;

	while(menu) {
		menu = print_menu();

		if (menu == 1) {
			game = GAME_START;
			menu = game_start();
		}
		else if (menu == 2) {
			//print_result();
		}
		else if (menu == 3) {
			printf("\t\t\t게임을 종료합니다...\n");
			sleep(1);
			system("clear");
			exit(0);
		}
	}

	return 0;
}

/* 메뉴를 출력하는 함수 */
int print_menu(void)
{
	int menu = 0;

	while(1) {
		system("clear");
		printf("\n\n\t\t\t\t   Sudoku!");
		printf("\n\t\t\t============================");
		printf("\n\t\t\t\t  GAME MENU\t\n");
		printf("\n\t\t\t============================");
		printf("\n\t\t\t   1) Game Start");
		printf("\n\t\t\t   2) Save Result");
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

// 게임 시작 전, 난이도를 설정하는 메뉴
void selectMode(void) {
	int mode = 0;

	while(1) {
		system("clear");
		printf("\n\n\t\t\t\t   Sudoku!");
		printf("\n\t\t\t============================");
		printf("\n\t\t\t\t  SELECT MODE\t\n");
		printf("\n\t\t\t============================");
		printf("\n\t\t\t   1) EASY");
		printf("\n\t\t\t   2) HARD");
		printf("\n\t\t\t============================");
		printf("\n\t\t\t\t\t SELECT : ");
		scanf("%d",&mode);
		
		if (mode < 1 || mode > 2) {	// 예외 처리
			printf("\t\t\t1-2 사이의 숫자를 입력해 주세요!\n");
			sleep(1);
			continue;
		}
		else if (mode == 1) {
			MODE = EASY;
			return;
		}
		else {
			MODE = HARD;
			return;
		}
	}
}
	

/* 게임 시작시 호출되는 함수.
game변수를 참조하여 게임을 종료하거나 시작함. 
게임 시작시 타이머를 등록함.
자식 프로세스에서 스도쿠를 만들고
부모 프로세스에서 사용자로 부터 숫자를 입력받음
*/
int game_start(void)
{
	/*static struct sigaction sa;
	static struct itimerval timer;
	time_t ptime;
	struct tm *t;

	if(game == GAME_START)
	{
		system("clear");
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
				return 1;
			}
		}
	}

	return 0;*/
	selectMode();
	printf("\t\t\t게임을 시작합니다!\n");
	sleep(1);
	int fd1[2], fd2[2];
	pid_t pid;
	int buf[3];
	int len, status, count, row, column;
	char input;

	if (pipe(fd1) == -1) {
		perror("pipe");
		exit(1);
		return 0;
	}

	if (pipe(fd2) == -1) {
		perror("pipe");
		exit(1);
		return 0;
	}

	switch(pid = fork()) {
		case -1:
			perror("fork");
			exit(1);
			return 0;

		case 0:	//child
			count = MODE;
			init_sudoku_table();
			close(fd1[1]);
			close(fd2[0]);
			write(fd2[1], sudoku_input, sizeof(sudoku_input));
			while(1) {
				len = read(fd1[0], buf, sizeof(buf));
				if (buf[2] == -1) {
					buf[0] = -1;
					write(fd2[1], buf, 1);
					break;
				}
				
				if (sudoku_table[buf[0], buf[1]] == buf[2])
					buf[0] = 1;
				else
					buf[0] = 0;
				write(fd2[1], buf, 1);
			}
			sleep(2);
			return;

		default:	//parent
			close(fd1[0]);
			close(fd2[1]);
			len = read(fd2[0], sudoku_input, sizeof(sudoku_input));
			while(1) {
				system("clear");
				print_sudoku_table();
				printf("입력하려는 행과 열을 (행 숫자, 열 숫자) 형식으로 입력해 주세요: ");
				scanf("%d %d", &buf[0], &buf[1]);
				printf("(%d, %d)에 넣을 숫자를 입력해 주세요(-1 입력시 게임 종료): ", buf[0], buf[1]);
				getchar();
				scanf("%d", &buf[2]);
				getchar();
				write(fd1[1], buf, sizeof(buf));
				
				len = read(fd2[0], &input, 1);
				
                if (input == -1) {
                    break;
                }

                else if(input == 1) {
					printf("정답입니다!\n");
					row = buf[0];
					column = buf[1];
					input = buf[2];
					memcpy(sudoku_input[row, column], &input, sizeof(int));
					continue;
				}
				else {
					printf("오답입니다.. 다시 한번 생각해보세요!\n");
					continue;
				}

			}
			
			printf("게임을 종료합니다.\n");
			waitpid(pid, &status, 0);
			memset(sudoku_table, 0, sizeof(sudoku_table));
			memset(sudoku_input, 0, sizeof(sudoku_input));
			return 1;
	}
}


/* 현재의 스도쿠판을 출력하는 함수 */
void print_sudoku_table(void)
{
	int i = 0, j = 0;
	
	printf("| 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │\n");
	printf("┌───┬───┬───┬───┬───┬───┬───┬───┬───┬ ───\n");

	for(;i < 9; i++) {
		j = 0;
		
		for(;j < 9; j++) {
			if(sudoku_input[i][j] == 0)
				printf("│   ");
			else
				printf("│ %d ", sudoku_input[i][j]);
		}
		printf("│  %d\n",i+1);
		
		if (i == 8)	// 마지막줄 출력 양식
			printf("└───┴───┴───┴───┴───┴───┴───┴───┴───┴ ───\n");
		
		else	// 이외의 줄
			printf("├───┼───┼───┼───┼───┼───┼───┼───┼───┼ ─── \n");
	}

	return;
}

/*메뉴에서 기록검색을 선택하면 기록을 파일로 출력하는 함수*/
/*int search_result(void)
{
	FILE *fp = NULL;
	char name[30];
	char ch;
	int find = 0;

	fp = fopen("result.txt", "r");

	if (fp == NULL) {
		perror("fopen");
		return 0;
	}

	system("clear");

	printf("\n\n\t\tWriting result to file...");

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

bool unUsedInBox(int rowStart, int colStart, int num) {
  for (int i = 0; i < SRN; i++)
    for (int j = 0; j < SRN; j++)
      if (sudoku_table[rowStart + i][colStart + j] == num)
        return false;

  return true;
}

bool unUsedInRow(int i, int num) {
  for (int j = 0; j < N; j++)
    if (sudoku_table[i][j] == num)
      return false;
  return true;
}

// check in the row for existence 
bool unUsedInCol(int j, int num) {
  for (int i = 0; i < N; i++)
    if (sudoku_table[i][j] == num)
      return false;
  return true;
}

bool CheckIfSafe(int i, int j, int num) {
  return (unUsedInRow(i, num) &&
    unUsedInCol(j, num) &&
    unUsedInBox(i - i % SRN, j - j % SRN, num));
}

// 한 칸에 숫자를 넣는 함수
void fillBox(int row, int col) {
  int num;
  srand(time(NULL));
  for (int i = 0; i < SRN; i++) {
    for (int j = 0; j < SRN; j++) {
      do {
		num = (rand() % N) + 1;
	  }
      while (!unUsedInBox(row, col, num));

      sudoku_table[row + i][col + j] = num;
    }
  }
}

// SRN x SRN 행렬의 대각선을 채움
void fillDiagonal(void) {
  for (int i = 0; i < N; i = i + SRN)
    fillBox(i, i);
}

// 나머지 칸을 채운다 
bool fillRemaining(int i, int j) {
  if (j >= N && i < N - 1) {
    i = i + 1;
    j = 0;
  }
  if (i >= N && j >= N)
    return true;

  if (i < SRN) {
    if (j < SRN)
      j = SRN;
  }
  else if (i < N - SRN) {
    if (j == (int)(i / SRN) * SRN)
      j = j + SRN;
  }
  else {
    if (j == N - SRN) {
      i = i + 1;
      j = 0;
      if (i >= N)
        return true;
    }
  }

  for (int num = 1; num <= N; num++) {
    if (CheckIfSafe(i, j, num)) {
      sudoku_table[i][j] = num;
	  
      if (fillRemaining(i, j + 1))
        return true;

      sudoku_table[i][j] = 0;
    }
  }
  return false;
}

// mode에 따라 테이블에서 자리를 지운다
void removeKDigits(void) {
  int count = MODE;
  srand(time(NULL));
  while (count != 0) {
    int cellId = (rand() % (N * N)) + 1;
    int i = (cellId / N);
    int j = cellId % 9;
    if (j != 0)
      j = j - 1;

    if (sudoku_input[i][j] != 0) {
      count--;
      sudoku_input[i][j] = 0;
    }
  }
}

// 스도쿠 테이블 생성 함수
void init_sudoku_table(void) {
  fillDiagonal();
  fillRemaining(0, SRN);
  memcpy(sudoku_input, sudoku_table, sizeof(sudoku_table));
  removeKDigits();
}