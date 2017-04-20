#!/bin/bash
log_file="/usr/lib/nodejs/nodejsProject/NewsSys/log/NewsSys.log"

# ANodeNewsSys resource use records
echo "ANodeNewsSys nodejs project resource use: " >> $log_file
pm2 monit ANodeNewsSys >> $log_file
