#!/usr/bin/env bash
#################################
# 源码路径
DIR=`pwd`
# 工程名
FINALNAME=app
PORT=8089
#################################

WAR=$DIR/target/$FINALNAME.war
# 构建生产环境
function build_pro(){
	svn cleanup
	svn update
	server_dir=/home/tkd/webserver/$PORT/	
	mvn clean package -Dmaven.test.skip=true -Ppro
	ps -ef |grep $PORT|awk '{print $2}'|xargs kill -9
	rm -rf $server_dir/webapps/$FINALNAME*
	cp $WAR $server_dir/webapps/
	cd $server_dir 
	bin/startup.sh
}

# 构建测试环境
function build_test(){
	svn cleanup
	svn update
	server_dir=/home/tkd/webserver/$PORT/	
	mvn clean package -Dmaven.test.skip=true -Ptest
	ps -ef |grep $PORT|awk '{print $2}'|xargs kill -9
	rm -rf $server_dir/webapps/$FINALNAME*
	cp $WAR $server_dir/webapps/
	cd $server_dir 
	bin/startup.sh
}

# 构建到213环境
function build_dev(){
	svn cleanup
	svn update
	server_dir=/home/tkd/webserver/$PORT/	
	mvn clean package -Dmaven.test.skip=true -Pdev
	ps -ef |grep $PORT|awk '{print $2}'|xargs kill -9
	rm -rf $server_dir/webapps/$FINALNAME*
	cp $WAR $server_dir/webapps/
	cd $server_dir 
	bin/startup.sh
}

if [[ -n $1 ]] && [[ $1 = 'test' ]]; then
	# 构建测试环境包
	read -p "发布到 [测试] 环境 ? y/n :"  val
	if [[ -n $val ]] && [[ $val = 'y' ]]; then 
		build_test
	else 
		echo '取消操作'
	fi
elif [[ -n $1 ]] && [[ $1 = 'pro' ]]; then
	# 构建正是环境包
	read -p "发布到 [生产] 环境 ? y/n :"  val
	if [[ -n $val ]] && [[ $val = 'y' ]]; then 
		build_pro
	else 
		echo '取消操作'
	fi
elif [[ -n $1 ]] && [[ $1 = 'dev' ]]; then
	# 构建正是环境包
	read -p "发布到 [213测试] 环境 ? y/n :"  val
	if [[ -n $val ]] && [[ $val = 'y' ]]; then 
		build_dev
	else 
		echo '取消操作'
	fi
	
else
        echo './build.sh [option]'
        echo '------------ option -------------- '
        echo 'dev :     构建测试213环境.'
        echo 'test:     构建测试环境.'
        echo 'pro :     构建生产环境.'
fi

