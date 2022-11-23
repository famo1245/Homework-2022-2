/*
6.dat 파일로 부터 성적 정보를 입력받아
정렬한 후 6.out 파일로 출력하는 프로그램
*/
#include <stdio.h>	//fopen(), fclose(), fscanf(), fprintf() 함수 관련 헤더파일
#include <stdlib.h>
#define SIZE 5	// 학생 수 정의

typedef struct {	//학생 이름, 점수를 담을 구조체
	char name[5];
	int korean;
	int math;
	int english;
	int sum;
	double avg;
}student;

int main() {
	FILE *rfp, *wfp;	//각각 6.dat, 6.out에대한 파일 포인터
	int n;
	int korean_sum = 0;	//국어 점수 합
	int math_sum = 0;	//수학 점수 합
	int eng_sum = 0;	//영어 점수 합
	student score[SIZE];	//학생과 점수를 받을 구조체 배열

	
	if((rfp = fopen("6.dat", "r")) == NULL) {	//6.dat를 읽기 옵션으로 open, 실패시 null 반환
		perror("Open 6.dat");	//에러시 에러 메시지 출력
		exit(1);
	}

	if((wfp = fopen("6.out", "w")) == NULL) {	//6.out을 쓰기 옵션으로 open, 실패시 null 반환
		perror("Open 6.out");	//에러 메시지 출력
		exit(1);
	}

	int idx = 0;	//구조체 배열에 사용할 인덱스
	while((n=fscanf(rfp, "%s	%d %d %d", score[idx].name, &score[idx].korean, &score[idx].math, &score[idx].english)) != EOF) {
		//6.dat에서 파일의 끝에 도달할 때까지, 이름 국어점수 수학점수 영어점수를 읽어옴
		score[idx].sum = score[idx].korean + score[idx].math + score[idx].english;	//읽어온 정보로 점수 총합 구함
		score[idx].avg = (double)score[idx].sum/3;	//점수의 평균을 구함
		idx++;	//배열의 인덱스 증가
		if(idx == SIZE)	//학생 수만큼 인덱스가 커지면 break
			break;
	}

	for(int i=0; i<SIZE-1; i++) {	//bubble sort사용, 점수 총합을 기준으로 내림차순 정렬
		for(int j=0; j<SIZE-1; j++) {
			if(score[j].sum < score[j+1].sum) {	//swap
				student temp = score[j];
				score[j] = score[j+1];
				score[j+1] = temp;
				}
		}
	}
	
	fprintf(wfp, "	KOR	MATH	ENG	sum	ave\n");	//6.out에 헤더 쓰기
	for(int i=0; i<SIZE; i++) {	//정렬된 학생정보 6.out에 쓰기
		fprintf(wfp, "%s\t%d\t%d\t%d\t%d\t%f\n", score[i].name, score[i].korean, score[i].math, score[i].english, score[i].sum, score[i].avg);
		korean_sum += score[i].korean;	//국어점수 총점 계산
		math_sum += score[i].math;	//수학점수 총점 계산
		eng_sum += score[i].english;	//영어점수 총점 계산
	}
	fprintf(wfp, "	%.1f	%.1f	%.1f\n", (double)korean_sum/SIZE, (double)math_sum/SIZE, (double)eng_sum/SIZE);	//국어, 수학, 영어 평균 6.out에 쓰기

	fclose(rfp);	//파일 포인터 닫기
	fclose(wfp);
}
