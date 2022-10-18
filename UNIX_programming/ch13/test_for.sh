#!/usr/bin/bash
#
# test for
#
sum=0

for num in 1 2 3 4
do
	(( sum += num ))
done
echo $sum
