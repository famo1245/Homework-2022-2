#!/usr/bin/bash
#
# test while loop
#

count=1
sum=0
sum2=0
while (( count <= 1000 ))
do
    let sum+=count
    (( count += 1 ))
done

count=1
while (( count <= 10000 ))
do
	let sum2+=count
	let count+=1
done

echo "Sum(1~1000) : $sum"
echo "Sum(1~10000) : $sum2"
