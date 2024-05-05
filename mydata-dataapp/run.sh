#!/bin/bash

# Exit on error
set -e

# change to the directory of the script
cd "$(dirname "$0")"

# Package
/opt/apache-maven-3.9.6/bin/mvn clean package

# Build
docker build -t uc-dataapp .

# Run
cd ../true-connector
docker-compose up -d --force-recreate uc-dataapp-consumer

echo "Successfully deployed the dataapp"