#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

#define N 100000000

unsigned int s = 0;
pthread_mutex_t mutex;	// mutex 구조체

void *c(void *d) {
	int i;
	for (i = 0; i < N; i++) {
		pthread_mutex_lock(&mutex);	// 다른 프로세스가 실행 할 수 없게 lock을 걸음
		s++;
		pthread_mutex_unlock(&mutex); // 다른 프로세스가 실행 할 수 있게 lock을 해제
	}
	return NULL;
}

int main(void) {
	pthread_t t[2];
	pthread_mutex_init(&mutex, NULL);	// mutex 초기화
	int r;
	
	r = pthread_create(&t[0], NULL, c, NULL);	// thread 생성
	if (r != 0) {
		perror("thread create:");	// 에러 처리
		exit(1);
	}
	
	r = pthread_create(&t[1], NULL, c, NULL);	// thread 생성
	if (r != 0) {
		perror("thread create:");	// 예외 처리
		exit(2);
	}
	
	pthread_join(t[0], NULL);	// thread 실행
	pthread_join(t[1], NULL);	// thread 실행
	printf("%u\n", s);
	pthread_mutex_destroy(&mutex);	// mutex 종료
	
	return 0;
}
