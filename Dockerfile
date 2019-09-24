FROM openjdk:8-jdk-alpine
LABEL maintaner="timadeshola@gmail.com"
VOLUME /tmp/
EXPOSE 2222
ARG JAR_FILE=/target/ussd-application.jar
ADD ${JAR_FILE} ussd-application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","ussd-application.jar"]
