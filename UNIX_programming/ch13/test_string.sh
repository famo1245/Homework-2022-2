#!/usr/bin/bash
#
# test string
#
echo "Are you OK (y/n): "
read ans

if [[ $ans = [Yy]* ]]
then
    echo "Happy to hear it."
else
    echo "That's too bad."
fi
