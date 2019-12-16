## 不用修改 ##
#!/bin/bash

replyMsg1( ) {
    m_date=`date "+%Y-%m-%d %H:%M:%S"`
    m_shell=`printf "%30s" $0`
    m_rspcode=`printf "%30d" $1`
    m_detail=`printf "%100s" $2`
    echo "Will write result to ${CAGT_HOME}/tmp/.$$"
   # echo "["$m_date"]["$m_shell"]["$m_rspcode"]["$m_detail"]" >> ${CAGT_HOME}/tmp/.$$
}

replyMsg2( ) {
    m_date=`date "+%Y-%m-%d %H:%M:%S"`
    m_shell=`printf "%30s" $0`
    m_rspcode=`printf "%30d" $1`
    m_detail=`printf "%100s" $2`
    echo "Will write to ${CAGT_HOME}/tmp/.$PPID"
    echo "["$m_date"]["$m_shell"]["$m_rspcode"]["$m_detail"]" >> ${CAGT_HOME}/tmp/.$PPID
}

checkReturn(){ 
	local mg=$1
	local rv=$2
	if [[ ! ${rv} -eq "0" ]];then
		msg="ERROR: ${mg} error , return value is ["+${rv}+"]. "
		echo $msg
		replyMsg1 ${rv} ${msg}
		exit -1
	fi
}


