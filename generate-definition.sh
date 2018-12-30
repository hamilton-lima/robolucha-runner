#!/bin/bash
PWD=$(pwd)
docker run -it -v $PWD:/tmp robolucha-runner \
/usr/bin/java -cp /usr/src/app/robolucha-runner.jar com.robolucha.support.DefaultGameDefinitionFileCreator /tmp/default-gamedefinition.json

cat default-gamedefinition.json
