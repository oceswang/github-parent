FROM registry.cn-beijing.aliyuncs.com/swang/jdk8:latest
VOLUME /tmp
ADD github-sso-core-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 80