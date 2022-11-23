#! /bin/bash
#
# calculate fibonacci number under 100
#

i=0	# i값 초기화
j=1	# j값 초기화

while (( i<100 ))	# while문 사용, 조건식 i가 100미만인 동안
do	# while문의 시작점 c언어의 '{'와 같음
	echo "$i"	# 현재 i의 값 출력
	(( k = i + j ))	# 변수 k에 i와 j를 더한 값을 저장
	(( i = j ))	# 변수 i에 j의 값을 저장
	(( j = k ))	# 변수 j에 i의 값을 저장
done	# while 문의 종료 c언어의 '}'와 같음
