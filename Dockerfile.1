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
RUN ant 

# base image with JRE only
FROM openjdk:8-jre-alpine

# copy artifact build from the 'build environment' at /deploy folder
RUN mkdir -pv /usr/src/app
COPY --from=builder /usr/src/app/deploy /usr/src/app
RUN find /usr/src/app

CMD ["/usr/bin/java", "-jar", \
    "-Dlog4j.configuration=file:/usr/src/app/log4j.properties", \
    "/usr/src/app/robolucha-runner.jar", \
    "/usr/src/app/default-gamedefinition.json"]
