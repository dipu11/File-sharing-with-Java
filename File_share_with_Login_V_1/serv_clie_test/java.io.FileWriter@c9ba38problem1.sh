#!/bin/bash
q=`expr 1`
p=`wc -l $1|cut -d " " -f1`
for ((i=2;i<p;i++))
do
	
	echo `cut -d " " -f2 a.txt|head -$q|tail -1` 
	q=`expr $q + 2`
	
done
