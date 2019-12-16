## 不用修改 ##
#!/bin/bash

#######  follow need not modify  ######## 
BUILD_SCRIPT_DIR=$(cd "$(dirname "$0")"; pwd)
BUILD_ROOT_DIR=$BUILD_SCRIPT_DIR/..
BUILD_DEPLOY_DIR=$BUILD_ROOT_DIR/deploy
BUILD_TARGET_DIR=$BUILD_ROOT_DIR/target/dist


#**************************************************************
#*********使用说明*********************************************
Usage()
{
  echo 'Usage: buildM.sh <moduleName> [ PRD | DEV ]'
}

MY_NAME=$1
if [ "${MY_NAME}" = "" ];then
	Usage
	exit -1
fi

build_mode=$2
if [ "${build_mode}" != "PRD" ];then
	build_mode=DEV
fi

#***************************************************************
#############################业务逻辑开始#######################
echo ""
echo "    >    ******   start build module[${MY_NAME}] AT [${build_mode}] mode  ...  *****"
echo ""

############  CLEAN BUILD DIR  ############
echo "    >    -----  clean [${MY_NAME}] build dir  -------"
if [ -d $BUILD_DEPLOY_DIR ]; then
	printf "    >              Clean:  $BUILD_DEPLOY_DIR ... " %s
	rm -rf $BUILD_DEPLOY_DIR
	echo "OK!"
fi

if [ -d $BUILD_ROOT_DIR/target ]; then
	printf "    >              Clean:  $BUILD_TARGET_DIR ... " %s
	rm -rf $BUILD_ROOT_DIR/target
	echo "OK!"
fi
echo "    >    -----  clean [${MY_NAME}] build dir , OK!  -------"
###########  building  ###############
echo "    >    -----  building [${MY_NAME}] ...   -------"
cd $BUILD_ROOT_DIR
if [ "${build_mode}" = "PRD" ];then
	mvn package -P publish up:collect -Dbugzilla=$BUGID -Dtd=8888 -Dcvsrepo=cvsnew2 -DmoduleId=1111 -DmoduleName=$MODULE -Dpath=$HOME/maventrace -DserverName=upjas -DserverVersion=2.0.1 -DcheckUrl=http://172.17.249.10/newsysservice/ipoc.asmx/PatchIsExist -DcheckC=false  --settings=/usr/local/bin/apache-maven-3.0.4/conf/settings_project.xml
else
	mvn package -P publish
fi

if [ ! $? -eq 0 ];then
	echo "    >  ERROR : [${MY_NAME}] mvn package error !!!"
	exit -1 
fi
echo "    >    -----  building [${MY_NAME}] , OK!!  -------"
############  package ... ############
echo "    >    -----  package [${MY_NAME}] ...   -------"
mkdir -p $BUILD_DEPLOY_DIR
if [ ! $? -eq 0 ];then
	echo "    >    -----  ERROR : [${MY_NAME}]  mkdir  $BUILD_DEPLOY_DIR error !!!"
	exit -1 
fi

mv $BUILD_TARGET_DIR/${MY_NAME}-deploy/install $BUILD_DEPLOY_DIR/
if [ ! $? -eq 0 ];then
	echo "    >    -----  ERROR : mv  [$BUILD_TARGET_DIR/${MY_NAME}-deploy/install]  to [$BUILD_DEPLOY_DIR]  error  !!!"
	exit -1 
fi

cd $BUILD_TARGET_DIR/${MY_NAME}-deploy
tar cfz ${MY_NAME}.tar.gz *
if [ ! $? -eq 0 ];then
	echo "    >      ERROR : tar [$BUILD_TARGET_DIR/${MY_NAME}-deploy]  to [${MY_NAME}.tar.gz]  error  !!!"
	exit -1 
fi


cp  $BUILD_TARGET_DIR/${MY_NAME}-deploy/${MY_NAME}.tar.gz $BUILD_DEPLOY_DIR/
if [ ! $? -eq 0 ];then
	echo "    >      ERROR : cp from [$BUILD_TARGET_DIR/${MY_NAME}-deploy/${MY_NAME}.tar.gz]  to [$BUILD_DEPLOY_DIR]  error  !!!"
	exit -1 
fi

echo ""
echo "    >    -------  package [${MY_NAME}] , OK !!!  -------"
#########  cleaning ... #######
printf "    >    -------  cleaning temp of [${MY_NAME}]  ...  " s%
rm -rf $BUILD_ROOT_DIR/target
echo " OK  !!!!  ------- "

echo "    > ----------------------------------------"
echo "          MODULE [${MY_NAME}]  build  SUCC!!!!"
echo "    > -----------------------------------------"
