#!/bin/bash

source video.sh

export ACT=${1}

function startjar() {
 echo "start"
    mvn clean package -U -Dmaven.test.skip=true \
    -Djava.net.preferIPv4Stack=true
    cd target
    echo $PWD
    if [ -e "$JAVA_NAME-$JAVA_VERSION" ]; then
        echo "BUILD SUCCESS"
    else
        echo "BUILD ERROR"
        return
    fi
    echo $JAVA_NAME-$JAVA_VERSION

    rm -rf /usr/local/51well/jar/$JAVA_NAME-$JAVA_VERSION

    mv $JAVA_NAME-$JAVA_VERSION /usr/local/51well/jar/

    cd /usr/local/51well/jar/
    echo $PWD
    nohup java -jar $JAVA_NAME-$JAVA_VERSION \
    >> $JAVA_NAME-$JAVA_VERSION.log 2>&1 & 
}

function stopjar(){
    if [ "$UNAME"x == "Msys"x ]; then
     PID=$(netstat -aon|findstr $(PORT) | grep "0.0.0.0:8081"|awk '{print $5}')
     echo $PID
     cmd /c "taskkill /pid ${PID} -f"
    else
     PID=$(ps -ef|grep $JAVA_NAME |grep -v grep | awk '{print $2}')
     echo ${PID}
     kill -9 ${PID}
     echo [INFO] kill -9 ${PID}
   fi
}



#启动
if [ "$ACT"x == "start"x ]; then
   startjar
#停止
elif [ "$ACT"x == "stop"x ]; then
    stopjar
#重启
else 
    echo "restart"
    stopjar

    startjar
fi




