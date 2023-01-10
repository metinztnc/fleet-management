FROM openjdk:11
MAINTAINER metin
VOLUME /tmp
EXPOSE 8080
ADD target/tfmbackend-0.0.1.jar tfmbackend.jar
ENTRYPOINT ["java","-jar","/tfmbackend.jar"]