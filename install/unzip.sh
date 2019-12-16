## 不用修改 ##
#!/usr/bin/sh

CUR_PATH=$(cd "$(dirname "$0")"; pwd)
. ${CUR_PATH}/cagtMsg.sh
#**************************************************************
#*********使用说明*********************************************
Usage()
{
  echo 'Usage: unzip.sh <module>'
}

module=$1
if [ "${module}" = "" ];then
	Usage
	checkReturn " Plz set module ... " "1"
fi

#***************************************************************
#############################业务逻辑开始#######################

printf "        >     Unzip modile [ ${module} ]  ... "
#Usage
cd $CUR_PATH/..
if [ ! -f ${module}.tar.gz ];then
	checkReturn " Cannot found ${module}.tar.gz in path[$CUR_PATH/..] ... " "1"
fi
tar zxf ${module}.tar.gz
checkReturn " Unzip ${module}.tar.gz in path[$CUR_PATH/..] ... " "$?"
echo " OK!"

if [ -f lib/activity_container*.tar.gz ];then
	echo "        >     Found activity_container*.tar.gz , unzip ... "
	mv lib/activity_container*.tar.gz install/
	bash install/acUnzip.sh
	checkReturn " Unzip activity_container*.tar.gz in path[ $CUR_PATH/../install ] ... " "$?"
else
	echo "        >     Warn: NOT Found activity_container*.tar.gz in path [ $CUR_PATH/../lib ]"
fi
