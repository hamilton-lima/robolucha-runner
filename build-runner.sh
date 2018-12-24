#!/bin/bash
cp build.xml docker/runner
cp ivy.xml docker/runner
cp -r src docker/runner

docker build docker/runner --tag robolucha-runner

rm docker/runner/build.xml
rm docker/runner/ivy.xml
rm -rf docker/runner/src
