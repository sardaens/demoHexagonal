FROM anapsix/alpine-java
LABEL maintainer="ardaens.stephane@gmail.com"
COPY /target/demoHexagonal-0.0.1-SNAPSHOT.jar /home/app.jar
CMD ["java","-jar","/home/app.jar"]
