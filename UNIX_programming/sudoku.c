// TODO: 함수 이름 바꾸기
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <sys/time.h>
#include <sys/times.h>
#include <unistd.h>
#include <sys/types.h>
#include <time.h>
#include <stdbool.h>
#include <sys/wait.h>

#define GAME_START 0 // 게임 시작,
#define GAME_END 1	// 종료 정의
#define N 9 // 행과 열
#define SRN 3 // 사각형의 행렬 크기
#define EASY 20 // 쉬움 모드
#define HARD 30 // 어려움 모드

// 게임 종료때 마다 사용자가 입력한 스도쿠 테이블, 플레이 시간이 저장되는 구조체
typedef struct result {	
	int user_input[N][N];
	int year;
	int month;
	int day;
	int hour;
	int min;
	double playTime;
}temp_result;

int print_menu(void); // 메뉴를 출력하는 함수
void selectMode(void);	// 게임 시작 전, 난이도를 설정하는 메뉴
void print_sudoku_table(void); // 현재의 스도쿠판을 출력하는 함수
void print_to_file(FILE *wfp);	// 결과를 파일로 저장하는 함수
int print_result(void); // 메뉴에서 기록 보기를 선택하면 기록을 파일과 화면으로 출력하는 함수
int game_start(void); /* 게임 시작시 호출되는 함수.
game변수를 참조하여 게임을 종료하거나 시작함. 
게임 시작시 타이머를 등록함.
자식 프로세스에서 스도쿠를 만들고
부모 프로세스에서 사용자로 부터 숫자를 입력받음
게임이 끝나면 사용자가 입력한 스도쿠 테이블,
플레이 시간, 플레이 한 날짜를 저장함
*/
int print_result(void);	// 메뉴에서 기록 보기를 선택하면 기록을 파일과 화면으로 출력하는 함수
bool unUsedInBox(int rowStart, int colStart, int num);
bool unUsedInRow(int i, int num);
bool CheckIfSafe(int i, int j, int num);
bool unUsedInCol(int j, int num);
void fillBox(int row, int col);	// 한 칸에 숫자를 넣는 함수
void fillDiagonal(void);
bool fillRemaining(int i, int j);
void removeKDigits(void);	// 빈칸을 만드는 함수
void init_sudoku_table(void);	// 스도쿠판을 생성하는 함수

int game = GAME_END; // 게임 상태 변수, 게임이 시작되거나 종료됨에 따라 변함
int MODE = EASY; // 게임의 난이도 변수, 사용자의 설정에 따라 변함, 기본 값은 쉬움
int sudoku_table[N][N] = {0, };	// 게임에서 생성된 스도쿠 판, 0으로 채워짐
int sudoku_input[N][N];	// 사용자가 입력하게 될 스도쿠 판
temp_result result;	// 게임의 결과가 저장되는 구조체


int main(void) {
	int menu = 1;

	while(menu) {
		menu = print_menu();

		if (menu == 1) {
			game = GAME_START;
			menu = game_start();
		}
		else if (menu == 2) {
			print_result();
		}
		else if (menu == 3) {
			printf("\t\t\t게임을 종료합니다...\n");
			sleep(1);
			system("clear");
			break;
		}
	}

	return 0;
}

// 메뉴를 출력하는 함수
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
부모 프로세스에서 사용자로 부터 숫자를 입력받음.
게임이 끝나면 사용자가 입력한 스도쿠 테이블,
플레이 시간, 플레이 한 날짜를 저장함
*/
int game_start(void)
{
	if (game == GAME_START) {
		selectMode();
		printf("\t\t\t게임을 시작합니다!\n");
		sleep(1);
		int fd1[2], fd2[2];
		clock_t start, end, ct;
		struct tms tms;
		struct tm *tm;
		time_t timep;
		pid_t pid;
		int buf[3];
		int len, status, count, row, column, check;
		int input;

		ct = sysconf(_SC_CLK_TCK);
		start = times(&tms);

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
						check = -1;
						write(fd2[1], &check, sizeof(int));
						break;
					}
					
					column = buf[0] - 1;
					row = buf[1] - 1;
					input = buf[2];
					if (sudoku_table[column][row] == input) {
						check = 1;
						count--;
					}
					else
						check = 2;
					if (count == 0) {
						check = 0;
						write(fd2[1], &check, sizeof(int));
						break;
					}
					write(fd2[1], &check, sizeof(int));
				}
				
				exit(0);

			default:	//parent
				close(fd1[0]);
				close(fd2[1]);
				len = read(fd2[0], sudoku_input, sizeof(sudoku_input));
				while(1) {
					system("clear");
					print_sudoku_table();
					
					printf("입력하려는 행과 열을 (행 숫자 열 숫자) 형식으로 입력해 주세요: ");
					scanf("%d %d", &buf[0], &buf[1]);
					getchar();
					
					if ((buf[0] > 9 || buf[0] < 0) || (buf[1] > 9 || buf[1] < 0)) {	// 입력이 행과 열 숫자를 벗어났을 때 예외
						printf("행과 열의 숫자는 1~9사이 입니다.\n");
						sleep(2);
						continue;
					}
					
					if (sudoku_input[buf[0]-1][buf[1]-1] != 0) {	// 빈칸이 아닌 곳을 입력하였을 때 예외
						printf("빈 칸에 해당하는 숫자를 입력해 주세요!\n");
						sleep(2);
						continue;
					}
					
					printf("(%d, %d)에 넣을 숫자를 입력해 주세요(-1 입력시 게임 종료): ", buf[0], buf[1]);
					scanf("%d", &buf[2]);
					getchar();
					
					if (buf[2] > 9 || buf[2] < -1 || buf[2] == 0)  {	// 값이 유효한 범위 (-1, 1~9)가 아닐 때 예외
						printf("유효한 범위의 값을 입력해 주세요!\n");
						sleep(2);
						continue;
					}
					
					column = buf[0] - 1;
					row = buf[1] - 1;
					input = buf[2];
		
					write(fd1[1], buf, sizeof(buf));				
					len = read(fd2[0], &check, sizeof(int));
					
					if (check == -1) {	// 게임 강제 종료
						printf("게임 종료 입력!\n");
						break;
					}
					else if(check == 1) {	// 정답
						printf("정답입니다!\n");
						sudoku_input[column][row] = input;
						sleep(2);
						continue;
					}
					else if(check == 0) {	// 퍼즐을 모두 채운 경우
						sudoku_input[column][row] = input;
						system("clear");
						print_sudoku_table();
						printf("축하드립니다! 퍼즐을 모두 채우셨습니다!\n");
						break;
					}
					else {	// 오답
						printf("오답입니다.. 다시 한번 생각해보세요!\n");
						sleep(2);
						continue;
					}
				}
				waitpid(pid, &status, 0);
		}
		
		end = times(&tms);
		printf("게임을 종료합니다.\n");
		sleep(2);
		game = GAME_END;
		result.playTime = (double) (end - start) / ct;
		time(&timep);
		tm = localtime(&timep);
		result.year = 1900+tm->tm_year;
		result.month = 1+tm->tm_mon;
		result.day = tm->tm_mday;
		result.hour = tm->tm_hour;
		result.min = tm->tm_min;
		memcpy(&result.user_input, sudoku_input, sizeof(sudoku_input));
		memset(sudoku_table, 0, sizeof(sudoku_table));
		memset(sudoku_input, 0, sizeof(sudoku_input));
		return 1;
	}

	return 0;
}


// 현재의 스도쿠판을 출력하는 함수
void print_sudoku_table(void)
{
	int i = 0, j = 0;
	
	printf("| 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │\n");
	printf("┌───┬───┬───┬───┬───┬───┬───┬───┬───┬ ───\n");

	for(;i < 9; i++) {
		j = 0;
		
		for(;j < 9; j++) {
			if(sudoku_input[i][j] == 0) {
				if (j == 3 || j == 6)
					printf("\x1b[31m│\x1b[0m   ");	// 3 * 3 구분을 위해
				else
					printf("│   ");
			}
			else {
				if (j == 3 || j == 6)
					printf("\x1b[31m│\x1b[0m %d ", sudoku_input[i][j]);	// 3 * 3 구분을 위해
				else
					printf("│ %d ", sudoku_input[i][j]);
			}
		}
		printf("│  %d\n",i+1);
		
		if (i == 8)	// 마지막줄 출력 양식
			printf("└───┴───┴───┴───┴───┴───┴───┴───┴───┴ ───\n");
		
		else {	// 이외의 줄
			if(i == 2 || i == 5)
				printf("├\x1b[31m───┼───┼───┼───┼───┼───┼───┼───┼───\x1b[0m┼ ─── \n");
			else
				printf("├───┼───┼───\x1b[31m┼\x1b[0m───┼───┼───\x1b[31m┼\x1b[0m───┼───┼───┼ ─── \n");
		}
	}

	return;
}

void print_to_file(FILE *wfp)
{
	int i = 0, j = 0;
	
	fprintf(wfp, "| 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │\n");
	fprintf(wfp, "┌───┬───┬───┬───┬───┬───┬───┬───┬───┬ ───\n");

	for(;i < 9; i++) {
		j = 0;
		
		for(;j < 9; j++) {
			if(sudoku_input[i][j] == 0)
				fprintf(wfp, "│   ");
			else
				fprintf(wfp, "│ %d ", sudoku_input[i][j]);
		}
		fprintf(wfp, "│  %d\n",i+1);
		
		if (i == 8)	// 마지막줄 출력 양식
			fprintf(wfp, "└───┴───┴───┴───┴───┴───┴───┴───┴───┴ ───\n");
		
		else	// 이외의 줄
			fprintf(wfp, "├───┼───┼───┼───┼───┼───┼───┼───┼───┼ ─── \n");
	}

	fprintf(wfp, "\n========================================================\n");
	fprintf(wfp, "%d년 %d월 %d일 %d:%d\n", result.year, result.month, result.day, result.hour, result.min);
	fprintf(wfp, "총 플레이 시간: %.1f 초\n", result.playTime);
	return;
}

// 메뉴에서 기록 보기를 선택하면 기록을 파일과 화면으로 출력하는 함수
int print_result(void)
{
	char input;
	memcpy(sudoku_input, &result.user_input, sizeof(sudoku_input));	// 이전 결과를 복사
	
	FILE *wfp = NULL;	// result.txt로 저장
	wfp = fopen("result.txt", "w");
	if (wfp == NULL) {
		perror("fopen");
		return 0;
	}
	print_to_file(wfp);
		
	while(1) {
		getchar();
		system("clear");
		print_sudoku_table();
		printf("\n========================================================\n");
		printf("%d년 %d월 %d일 %d:%d\n", result.year, result.month, result.day, result.hour, result.min);
		printf("총 플레이 시간: %.1f 초\n", result.playTime);

		printf("========================================================\n");
		printf("M 입력시 메뉴로 돌아갑니다: ");
		scanf("%c", &input);

		if (input == 'm' || input == 'M')
			break;
	}

	return 1;
}

// 3*3 상자 안에 같은 숫자가 있는지 확인하는 함수
bool unUsedInBox(int rowStart, int colStart, int num) {
  for (int i = 0; i < SRN; i++)
    for (int j = 0; j < SRN; j++)
      if (sudoku_table[rowStart + i][colStart + j] == num)
        return false;

  return true;
}

// 같은 열에 동일한 숫자가 있는지 확인하는 함수
bool unUsedInRow(int i, int num) {
  for (int j = 0; j < N; j++)
    if (sudoku_table[i][j] == num)
      return false;
  return true;
}

// 같은 행에 동일한 숫자가 있는지 확인하는 함수
bool unUsedInCol(int j, int num) {
  for (int i = 0; i < N; i++)
    if (sudoku_table[i][j] == num)
      return false;
  return true;
}

// 위의 세 함수를 사용하여, 같은 수가 있는지 확인하는 함수
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

// 3 * 3 상자의 대각선을 채움
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