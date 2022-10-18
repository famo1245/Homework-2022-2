#!/usr/bin/bash
#
# test case
#
echo "Select command : "
read cmd

case $cmd in
    [0-9]) # 0~9 digit
	date
	;;
    l | L) # l or L
	ls
	;;
    p | P) # p or P
	pwd
	;;
    *)
	echo "Usage : select command"
	;;
esac
