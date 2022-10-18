#!/usr/bin/bash
#
# test file type
#
echo "Input file name : "
read file

if [[ ! -e $file ]] # if file exists
then
    echo "$file File not exists."
elif [[ -d $file ]]
then
	echo "$file is a directory."
else
	echo "$file is a special file."
fi
