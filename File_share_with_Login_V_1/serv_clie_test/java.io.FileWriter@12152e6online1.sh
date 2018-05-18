#!/bin/bash
f=0
count=0;
for i in $(less input) 
do

if [ $f = 1 ];then
var=$i
fi
if [ $i = find ];then
f=1
fi


done



for j in $(less input) 
do
count=`expr $count + 1`

if [ $count = $var ];then
echo $j
fi

done
