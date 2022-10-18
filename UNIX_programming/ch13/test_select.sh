#!/bin/bash
#
# test select
#

PS3="Input command(1~3) : "

select cmd in pwd date quit
do
	case $cmd in
		pwd) pwd;;
		date) date;;
		quit) break;;
		*) echo "Invalid input, select number" ;;
	esac

	REPLY=-1 # null
done
