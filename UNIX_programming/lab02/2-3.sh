#!/bin/bash
# 구구단 출력 프로그램

dan=2
cnt=1

while (( dan <= 9 ))
do
	while (( cnt <= 9 ))
	do
		echo -n $(( $dan * $cnt ))
	        echo -n " "	
		let cnt++
	done
	
	echo
       	cnt=1
	let dan++
done
