#!/bin/bash
docker run --name test-redis --rm -p 6379:6379 -d redis
