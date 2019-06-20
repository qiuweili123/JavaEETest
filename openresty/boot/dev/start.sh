#!/bin/bash
export JAVA_HOME=${JAVA_HOME8}
export PATH=$JAVA_HOME/bin:$PATH

cd `dirname $0`
DEPLOY_DIR=`pwd`
LOGS_DIR=$DEPLOY_DIR/logs
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

JAVA_OPTS=" -server -Xmx1g -Xms1g -Xss256k -XX:MaxMetaspaceSize=512M -XX:MetaspaceSize=256M -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/logs/dump "
SPRING_BOOT_OPTS="--spring.profiles.active=dev"

STDOUT_FILE=$LOGS_DIR/stdout.log
nohup java -jar $JAVA_OPTS  $DEPLOY_DIR/appms-1.0.1.jar $SPRING_BOOT_OPTS >> $STDOUT_FILE 2>&1 &

echo "OK!"
PIDS=`ps -ef | grep java | grep "$DEPLOY_DIR" |grep -v grep | awk '{print $2}'`
echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"