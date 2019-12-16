## 不用修改 ##
#!/usr/bin/sh

CUR_PATH=$(cd "$(dirname "$0")"; pwd)
. ${CUR_PATH}/cagtMsg.sh
MODULE_HOME=${CUR_PATH}/../..
#**************************************************************
#*********使用说明*********************************************
Usage()
{
  echo 'Usage: installM.sh <module> <version>'
}

my_module=$1
if [ "${my_module}" = "" ];then
	Usage
	exit -1
fi

my_version=$2
if [ "${my_version}" = "" ];then
	Usage
	exit -1
fi

#***************************************************************
#############################业务逻辑开始#######################

echo "#################################################################################################################"
echo "###########        Begin to install module group [${my_module}]  ver [${my_version}]  ...        ###############"
echo "#################################################################################################################"
	test -f ${MODULE_HOME}/${my_module}/install/activity_container*/bin/install.sh
	checkReturn " Found install cmd of [${MODULE_HOME}/${my_module}/install/activity_container*/bin/install.sh]..." "$?"
	bash ${MODULE_HOME}/${my_module}/install/activity_container*/bin/install.sh -v"${my_version}" -m"${my_module}"
	checkReturn " Install module [${my_module}] ..." "$?"
done
echo ""
echo "############################################################################################"
echo "   SUCC to install  docker [ ${my_module} of ver [${my_version} ]  !!!!"
echo "############################################################################################"
