# prepare builder
FROM openjdk:8-jdk-alpine as builder
RUN apk update
RUN apk add bash
RUN apk add apache-ant 

# install ivy
RUN wget http://central.maven.org/maven2/org/apache/ivy/ivy/2.4.0/ivy-2.4.0.jar \
    -O /usr/share/java/apache-ant/lib/ivy-2.4.0.jar

RUN mkdir -pv /usr/src/app/target

COPY . /usr/src/app
WORKDIR /usr/src/app

# build app
RUN ant resolve

