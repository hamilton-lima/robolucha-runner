#!/bin/bash
docker stop robolucha-runner
docker run -it --rm --name=robolucha-runner -d robolucha-runner
docker logs --follow robolucha-runner 
