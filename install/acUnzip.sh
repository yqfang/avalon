## 不用修改 ##
#!/usr/bin/sh
CUR_PATH=$(cd "$(dirname "$0")"; pwd)
. ${CUR_PATH}/cagtMsg.sh

printf "               >       Begin to unzip activity container   ... " %s
cd $CUR_PATH
if [ ! -f activity_container*.tar.gz ];then
	checkReturn " Cannot found activity_container*.tar.gz in path[$CUR_PATH] ... " "1"
fi
tar zxf activity_container*.tar.gz
echo " ok!"
