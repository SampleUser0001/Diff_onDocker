#!/bin/bash

export $(cat .env | grep -v ^# | xargs)
cd ${PROJECT_NAME}

MVN_COMMAND="clean compile exec:java"
mvn ${MVN_COMMAND}
