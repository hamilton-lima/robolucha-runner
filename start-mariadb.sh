#!/bin/bash
export MYSQL_ROOT_PASSWORD=foo123123
export MYSQL_DATABASE=robolucha_db
export MYSQL_USER=robolucha_uzr
export MYSQL_PASSWORD=foo123123

docker stop robolucha-mariadb

docker run --rm --name robolucha-mariadb \
-e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
-e MYSQL_DATABASE=$MYSQL_DATABASE \
-e MYSQL_USER=$MYSQL_USER \
-e MYSQL_PASSWORD=$MYSQL_PASSWORD \
-p 3306:3306 -d mariadb

docker logs robolucha-mariadb --follow