# reuse builder with jar files cached
FROM robolucha-runner-builder:latest as builder

# copy source to build in the builder
COPY . /usr/src/app
WORKDIR /usr/src/app

# build the app
RUN ant 

# base image with JRE only
FROM openjdk:8-jre-alpine

# copy artifact build from the 'build environment'
RUN mkdir -pv /usr/src/app
COPY --from=builder /usr/src/app/target /usr/src/app

CMD ["/usr/bin/java", "-jar", \
    "/usr/src/app/robolucha-runner.jar", \
    "/usr/src/app/default-gamedefinition.json"]
