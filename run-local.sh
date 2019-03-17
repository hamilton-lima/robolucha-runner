#!/bin/bash
export REDIS_HOST=localhost
export REDIS_PORT=6379
export INTERNAL_API_KEY=9239

cd deploy
java -jar -Dlog4j.configuration=log4j.properties robolucha-runner.jar default-gamedefinition.json
