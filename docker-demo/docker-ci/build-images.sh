#!/bin/bash --login

cd $DOCKER_PATH/otms-core
if
docker build -t otms-core:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/otms-rmi
if
docker build -t otms-rmi:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/otms-mobilews
if
docker build -t otms-mobilews:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/otms-tariff
if
docker build -t otms-tariff:$ENV_NAME .
then
echo 1
else
exit 1
fi


cd $DOCKER_PATH/otms-analysis
if
docker build -t otms-analysis:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/otms-mail
if
docker build -t otms-mail:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/otms-position
if
docker build -t otms-position:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/otms-search-manager
if
docker build -t otms-search-manager:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/otms-export
if
docker build -t otms-export:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/core-api
if
docker build -t core-api:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/otms-master-service
if
docker build -t otms-master-service:$ENV_NAME .
then
echo 1
else
exit 1
fi

cd $DOCKER_PATH/otms-master-service-helper
if
docker build -t otms-master-service-helper:$ENV_NAME .
then
echo 1
else
exit 1
fi
