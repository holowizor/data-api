ARG JAVA_VERSION=8u151
ARG DB_FILE_LOCATION="./"
ARG DB_FILE_NAME="data.h2"

FROM openjdk:${JAVA_VERSION}-jre

ENV DB_FILE_LOCATION=$DB_FILE_LOCATION
ENV DB_FILE_NAME=$DB_FILE_NAME

COPY ./target/*jar-with-dependencies.jar /devbuild/service.jar
WORKDIR /devbuild
EXPOSE 9090
CMD ["java","-jar","service.jar"]