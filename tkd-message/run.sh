#! /bin/sh

#入口main函数所在类
MAIN_CLASS=com.lc.zy.message.MessageMain

#如果没有 JAVA_HOME 变量，需要在此配置
#JAVA_HOME=/app/java/jdk1.7.0_45

set -x
cp="target/classes"
for i in `ls -1 target/lib/*.jar`; do
	cp=${cp}:./$i
done
$JAVA_HOME/bin/java -cp ${cp} -Xms512m -Xmx512m ${MAIN_CLASS} &
