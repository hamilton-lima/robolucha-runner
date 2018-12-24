#!/bin/bash
cp build.xml docker/builder
cp ivy.xml docker/builder

docker build docker/builder --tag robolucha-runner-builder

rm docker/builder/build.xml
rm docker/builder/ivy.xml
