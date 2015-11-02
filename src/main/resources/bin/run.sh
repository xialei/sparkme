#!/bin/sh

CLASSPATH="."

# travel all jars and add to CLASSPATH
for jarfile in `ls lib/.`
do
    CLASSPATH="${CLASSPATH}:lib/$jarfile"
done

# echo $CLASSPATH

# JAVA_OPTS="-Xms1024m -Xmx2048m -Xss512K -XX:PermSize=256m -XX:MaxPermSize=256m"

# --master 192.168.0.229:7077
usr/local/spark/bin/spark-submit --class com.aug3.test.sparkme.LastTradeDateCalculate --master local[2] --jars ${CLASSPATH} /home/roger/sparkme-0.0.1-SNAPSHOT.jar