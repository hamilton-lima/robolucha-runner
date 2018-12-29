#!/bin/bash
wget http://central.maven.org/maven2/io/swagger/swagger-codegen-cli/2.3.1/swagger-codegen-cli-2.3.1.jar -O swagger-codegen-cli.jar
java -jar swagger-codegen-cli.jar generate --output ./sdk --lang java --input-spec ../robolucha-api/docs/swagger/swagger.yaml
mv -r sdk/src/* ./src
rm swagger-codegen-cli.jar

