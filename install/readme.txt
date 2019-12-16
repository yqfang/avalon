【说明】
    这个是用来单个 docker project 的安装
    cfg 文件要根据实际情况修改

【步骤】
第一 拷贝：
     build.sh        拷贝到 <projecctHome>/build   目录下,并且配置里面的module变量
     剩余的所有        拷贝到 <projecctHome>/install 目录下
第二 配置：
     1 配置  pom.xml, 按照pom.xml.template中的描述， 引入activity_container.tar.gz 
                                                  activity_container.jar 和 acplg-monitor.tar.gz 根据实际需要，酌情引入
     2 配置  <projecctHome>/install/group.cfg                  用来配置需要装载的 acplg
     3 配置  <projecctHome>/install/install.cfg rollback.cfg   用来和自动化安装对接                            
                                                                                
【使用】
1 编译：
       bash <projecctHome>/install/buildM.sh <module>
      
2 解压
       bash <projecctHome>/install/unzip.sh <module>
            
3 安装：
       bash <projecctHome>/install/installM.sh <module> <version>
	 