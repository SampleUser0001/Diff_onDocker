#!/bin/bash

export $(cat .env | grep -v ^# | xargs)
cd ${PROJECT_NAME}

MVN_COMMAND="--version"
mvn ${MVN_COMMAND}
