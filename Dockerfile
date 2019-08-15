FROM openjdk:8-jre-slim
LABEL maintainer="ardaens.stephane@gmail.com"

ENV JVM_OPTIONS="-Djava.security.egd=file:/dev/./urandom -Xmx512M -XshowSettings:vm"
ADD ./target/demoHexagonal-0.0.1-SNAPSHOT.jar /home/app.jar

EXPOSE 8080

WORKDIR /home

ENTRYPOINT java $JVM_OPTIONS -jar app.jar
