#!/bin/bash

docker-compose -f docker-compose_init.yml up --build 

export $(cat .env | grep -v ^# | xargs)
echo 'USER='${USER}
echo 'PROJECT_NAME='${PROJECT_NAME}

sudo chown -R ${USER}:${USER} ${PROJECT_NAME}
