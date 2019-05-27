#!/bin/bash
export MYSQL_HOST=database
export MYSQL_ROOT_PASSWORD=76ecd53e73cd
export MYSQL_DATABASE=robolucha_db
export MYSQL_USER=robolucha_uzr
export MYSQL_PASSWORD=45f01c0c331c

docker exec -it dev-database mysql -uroot -p$MYSQL_ROOT_PASSWORD robolucha_db
