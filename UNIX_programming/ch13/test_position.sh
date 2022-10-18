#!/usr/bin/bash
# test positional parameter
#
echo '$*' : $*
echo '$#' : $#
echo '$@' : $@
set --
echo $1 $2 $3
