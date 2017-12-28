#!/bin/bash --login

docker ps -a | grep "Exited" | awk '{print $1 }'|xargs docker stop
docker ps -a | grep "Exited" | awk '{print $1 }'|xargs docker rm
docker images -a |grep none|awk '{print $3 }'|xargs docker rmi
docker volume ls | grep local | awk '{print $2}' | xargs docker volume rm
