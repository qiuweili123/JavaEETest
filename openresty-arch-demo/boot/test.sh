#!/bin/sh

#####################################################################

# usage:
# sh test.sh -- test application @dev
# sh test.sh ${env} -- test application @${env}

# examples:
# sh test.sh pro -- use conf/nginx-pro.conf to test lua_user-center
# sh test.sh -- use conf/nginx-dev.conf to test lua_user-center


#
#通过设置环境变量"ENV_CONFIG"来决定加载项目配置文件
#
#####################################################################
#Home 目录
APP_ROOT_DIR="$(cd `dirname $0`/../; pwd)"
#环境配置文件标识
env_conf_path="$HOME/.$(cd `dirname $0`/../; basename `pwd`)"

if [[ -n "$1" ]];then
	ENV_CONFIG="$1"
else
	 ENV_CONFIG="pro"
fi

if [[ ($ENV_CONFIG == "dev" || $ENV_CONFIG == "test" || $ENV_CONFIG == "pro") ]];then
	echo "当前环境:$ENV_CONFIG"
	echo $ENV_CONFIG > $env_conf_path
else
	echo""
	echo "脚本执行失败,请增加运行参数 dev 或 test 或 pro"
	exit 0
fi
	
export ENV_CONFIG

echo "Nginx的工作目录:${APP_ROOT_DIR} "

echo "Nginx的配置文件:conf/${ENV_CONFIG}/nginx.conf ==> conf/nginx.conf"
sed -e "s#{APP_ROOT_DIR}#${APP_ROOT_DIR}#g" "conf/${ENV_CONFIG}/nginx.conf" > conf/nginx.conf


echo "nginx -t -p ${APP_ROOT_DIR} -c conf/nginx.conf"
/usr/local/openresty/nginx/sbin/nginx -t -p ${APP_ROOT_DIR} -c conf/nginx.conf