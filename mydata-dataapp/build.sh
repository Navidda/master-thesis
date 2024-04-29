#!/bin/bash

# Package
/opt/apache-maven-3.9.6/bin/mvn clean package

# Build
docker build -t uc-dataapp .