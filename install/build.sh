### 需要根须实际情况配置变量 ${module}
#!/bin/bash

module=

if [ "${module}" = "" ];then
	echo " Plz set module First !!!"
	exit -1
fi

#######  follow need not modify  ######## 
my_dir=$(cd "$(dirname "$0")"; pwd)
bash ../install/buildM.sh "${module}" "PRD"