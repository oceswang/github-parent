FROM registry.cn-beijing.aliyuncs.com/swang/jdk:8
VOLUME [/tmp,/logs]
ADD service-gateway-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/app.jar"]
EXPOSE 8001