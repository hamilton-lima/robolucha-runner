#!/bin/bash
export MYSQL_ROOT_PASSWORD=foo123123
export MYSQL_DATABASE=robolucha_db
export MYSQL_USER=robolucha_uzr
export MYSQL_PASSWORD=foo123123

docker exec -it robolucha-mariadb mysql -uroot -p$MYSQL_ROOT_PASSWORD
