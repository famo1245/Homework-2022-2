#!/bin/bash
#
# test continue
#

for person in $(< list)
do
	if [[ $person == user2 ]]
	then
		continue
	fi

	echo "Hello, $person"
done
