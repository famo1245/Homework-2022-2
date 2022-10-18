#!/bin/bash
# 명령행 인자와 계산 기능 이용

sum=0
minus=0
mul=0
div=0
(( sum = $1 + $2 ))
(( minus = $1 - $2 ))
(( mul = $1 * $2 ))
(( div = $1 / $2 ))
echo "$1 + $2 = $sum"
echo "$1 - $2 = $minus"
echo "$1 * $2 = $mul"
echo "$1 / $2 = $div"
