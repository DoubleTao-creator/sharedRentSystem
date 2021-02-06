# Docker image for demo file run
# VERSION 0.0.1
# Author: smallgourd
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER xtt<2045519528@qq.com>
#系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名
ADD target/eureka-server-1.0-SNAPSHOT.jar eureka-server7001.jar
#启动容器时的进程
ENTRYPOINT ["java","-jar","/eureka-server7001.jar"]
#暴露端口
EXPOSE 7001
