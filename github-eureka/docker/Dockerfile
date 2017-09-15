FROM registry.cn-beijing.aliyuncs.com/swang/jdk8:latest
VOLUME /tmp
ADD github-eureka-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /usr/local/app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/app.jar"]
EXPOSE 8761