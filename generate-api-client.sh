#!/bin/bash

if [ ! -f "swagger-codegen-cli.jar" ]; then
    echo "downloading swagger-codegen-cli.jar"
   wget https://repo1.maven.org/maven2/io/swagger/codegen/v3/swagger-codegen-cli/3.0.0/swagger-codegen-cli-3.0.0.jar -O swagger-codegen-cli.jar

fi

java -jar swagger-codegen-cli.jar generate --output ./sdk --lang java --input-spec ../robolucha-api/docs/swagger.yaml

# remove unused file 
rm sdk/src/main/AndroidManifest.xml
rm sdk/src/test/java/io/swagger/client/api/DefaultApiTest.java

# fix unecessary imports on generated code 
if [ ! -f "google-java-format.jar" ]; then
    echo "downloading google-java-format.jar"
    wget https://github.com/google/google-java-format/releases/download/google-java-format-1.6/google-java-format-1.6-all-deps.jar -O google-java-format.jar
fi

java -jar google-java-format.jar --fix-imports-only -i $(find sdk/src -type f -name "*.java")

# removes unecessary anottation 
sed -i -e 's/@SuppressWarnings("rawtypes")//g' sdk/src/main/java/io/swagger/client/api/DefaultApi.java

cp -R sdk/src .
rm -rf sdk
