#!/bin/bash
docker run --name robolucha-redis --rm -p 6379:6379 -d redis
docker exec -it robolucha-redis redis-cli monitor
