#!/bin/bash --login

BASE_PATH=$(cd `dirname $0`; pwd)
DOCKER_PATH=$BASE_PATH/dockerFiles

source $BASE_PATH/.env

cd $DOCKER_PATH/..

docker-compose down

source build-images.sh

cd $DOCKER_PATH/..

source clean.sh

d=$(date "+%m%d%H%M%S")
docker-compose up -d #> $d.log
