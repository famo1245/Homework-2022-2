#~/usr/bin/bash
#
# if then else test
#
echo "Input x: "
read x
echo "Input y: "
read y

if (( x < y ))
then
	echo "$x is less than $y"
else
	echo "$y is less than $x"
fi
