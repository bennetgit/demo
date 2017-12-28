#!/bin/sh

#------------------------------------------
#script for start mail service from otms-engine
#author    : AtomYuansheng
#create At : 2015-01-22
#need set JAVA_HOME in conf/application.properties
#need set APP_LOG in conf/application.properties
#need set RMIIP in conf/application.properties
#------------------------------------------
APP_HOME=$(dirname $0)
APP_HOME=$APP_HOME/..
#JAVA_HOME=/usr/java/jdk1.7.0_75
#LOCAL DEBUG
#JAVA_HOME=/home/atomyuansheng/java/jdk1.7.0_51

APP_LOG=/var/log/opentms-engine

echo "APP_HOME is $APP_HOME"

#JAVA_HOME=`sed '/^javaHome=/!d;s/.*=//' $APP_HOME/conf/application.properties`
APP_LOG=`sed '/^rmiLogHome=/!d;s/.*=//' $APP_HOME/conf/application.properties`
RMIIP=`sed '/^rmiIP=/!d;s/.*=//' $APP_HOME/conf/application.properties`

serviceMate=RMIService

#-Djava.rmi.server.hostname=$RMIIP

JAVA_OPTS="-Duser.timezone=GMT+8 -server -Xms512m -Xmx1024m \
		-DspringFileName=applicationContext_otmsRMIService.xml \
		-DserviceMate=$serviceMate \
		-DlogDir=$APP_LOG \
		-DappHome=$APP_HOME"

APP_MAIN=com.opentrans.otms.base.StartRMIService

CLASSPATH=$APP_HOME/conf

for i in "$APP_HOME"/lib/*.jar
do
   CLASSPATH="$CLASSPATH":"$i"
done


mailServicePID=0

getRMIServicePID(){  
    applicationContext=applicationContext
	CLASS_MAIN=RMIService 
	javaps=`ps -ef | grep $CLASS_MAIN | grep $applicationContext`
    
    
    if [ -n "$javaps" ]; then  
        mailServicePID=`echo $javaps | awk '{print $2}'`  
    else  
        mailServicePID=0  
    fi  
}  
  
startup(){  
    getRMIServicePID  
    echo "================================================="  
    if [ $mailServicePID -ne 0 ]; then  
        echo "$APP_MAIN already started(PID=$mailServicePID)"  
        echo "================================================="    
    else  
        echo -n "Starting $APP_MAIN"  
#start APP        
        #nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAIN >/dev/null 2>&1 &


        $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAIN
#sleep 3 seconds
		sleep 3 
	    echo -n  " start log in $APP_LOG/otmsService_RMIService.log"       
        getRMIServicePID  
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
 getRMIServicePID
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
        getRMIServicePID  
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
		echo  "Starting RMI Service: "
		startup
		;;
	stop)
		echo  "Stop RMI Service: "
		showdown
		;;
	restart)
		showdown
		sleep 1
		startup
		;;
	*)
		echo "Usage: rmiService.sh [-d] {start|stop|restart}"
	;;
esac
exit 0	


