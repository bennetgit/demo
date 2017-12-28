#!/bin/sh

#------------------------------------------
#script for start mail service from otms-engine
#author    : AtomYuansheng
#create At : 2015-01-22
#need set JAVA_HOME
#------------------------------------------
APP_HOME=$(dirname $0)
APP_HOME=$APP_HOME/..
#JAVA_HOME=`sed '/^javaHome=/!d;s/.*=//' $APP_HOME/conf/application.properties`
APP_LOG=`sed '/^logHome=/!d;s/.*=//' $APP_HOME/conf/application.properties`
#MAILIP=`sed '/^mailIP=/!d;s/.*=//' $APP_HOME/conf/application.properties`

serviceMate=mailService

##################################################
# Get JAVA_OPTS
##################################################

JAVA_OPTS="-Duser.timezone=GMT+8 -server -Xms512m -Xmx1024m -DspringFileName=applicationContext.xml \
        -Djava.rmi.server.hostname=$MAILIP \
        -DserviceMate=$serviceMate \
        -DlogDir=$APP_LOG \
        -DappHome=$APP_HOME"

APP_MAIN=com.opentrans.otms.base.StartService

CLASSPATH=$APP_HOME/conf
for i in "$APP_HOME"/lib/*.jar
do
   CLASSPATH="$CLASSPATH":"$i"
done



mailServicePID=0

getMailServicePID(){  
    applicationContext=applicationContext
    CLASS_MAIN=mailService 
    javaps=`ps -ef | grep $CLASS_MAIN | grep $applicationContext`
    
    if [ -n "$javaps" ]; then  
        mailServicePID=`echo $javaps | awk '{print $2}'`  
    else  
        mailServicePID=0  
    fi  
}  
  
startup(){  
    getMailServicePID  
    echo "================================================="  
    if [ $mailServicePID -ne 0 ]; then  
        echo "$APP_MAIN already started(PID=$mailServicePID)"  
        echo "================================================="    
    else  
        echo -n "Starting $APP_MAIN"  
	
	sleep 20
#start APP        
        $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAIN
#sleep 3 seconds
        sleep 3 
    echo -n  " start log in $APP_LOG/startOtmsService_$serviceMate.log"
        getMailServicePID  
        if [ $mailServicePID -ne 0 ]; then  
            echo "(PID=$mailServicePID)...[Success]"  
            echo "================================================="    
        else  
            echo "[Failed To Start]"  
            echo "================================================="    
        fi  
    fi  
}  


showdown(){
 getMailServicePID
    echo "================================================="  
    if [ $mailServicePID -ne 0 ]; then  
        echo -n "Stopping $APP_MAIN (PID=$mailServicePID)..."  
        kill -9 $mailServicePID  
        if [ $? -eq 0 ]; then  
            echo "[Stop $APP_MAIN Success]"  
            echo "================================================="   
        else  
            echo "[Failed to Stop $APP_MAIN]"  
            echo "================================================="    
        fi  
        getMailServicePID  
        if [ $mailServicePID -ne 0 ]; then  
            shutdown  
        fi  
    else  
        echo "$APP_MAIN is not running"  
        echo "================================================="    
    fi  
}

##################################################
# Get the action
##################################################

ACTION=$1

case "$ACTION" in
    start)
        echo  "Starting Mail Service: "
        startup
        ;;
    stop)
        echo  "Stop Mail Service: "
        showdown
        ;;
    restart)
        showdown
        sleep 1
        startup
        ;;
    *)
        echo "Usage: mailService.sh [-d] {start|stop|restart}"
    ;;
esac
exit 0  
