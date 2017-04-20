#!/bin/bash

log_file="/usr/lib/nodejs/nodejsProject/NewsSys/log/NewsSys.log"
pidlist=$(pgrep -f pm2) 
echo $pidlist
echo "start log" > $log_file
if [ "$pidlist" == "" ];  then
	cd /usr/lib/nodejs/nodejsProject/NewsSys/log
	echo "no pm2 pid alive!" >> $log_file
	echo "now start pm2" >> $log_file
	pm2 -v >> $log_file
	pidlist=$(pgrep -f pm2)
	echo "now pm2 started,pid is $pidlist" >> $log_file
fi

# start node project
cd /usr/lib/nodejs/nodejsProject/NewsSys
pm2 start process.json --watch >> $log_file

# check nodejs project msg
echo "all nodejs project msg: " >> $log_file
pm2 list >> $log_file

# check ANodeNewsSys despriction
echo "ANodeNewsSys nodejs project detail : " >> $log_file
pm2 describe ANodeNewsSys >> $log_file



