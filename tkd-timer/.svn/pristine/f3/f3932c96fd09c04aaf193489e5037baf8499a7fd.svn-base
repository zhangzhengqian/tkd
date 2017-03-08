#################################
# 源码路径
DIR=`pwd`
#################################
# 构建生产环境
function build_pro(){
	svn cleanup
	svn update
	mvn clean install -Dmaven.test.skip=true -U -Ppro
}

# 构建测试环境
function build_test(){
	svn cleanup
	svn update
	mvn clean install -Dmaven.test.skip=true -U -Ptest
}

# 构建到213环境
function build_dev(){
	svn cleanup
	svn update
	mvn clean install -Dmaven.test.skip=true -U -Pdev
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

