#!/bin/bash
# This script copies the dataset from the dataset folder to the dataapp_provider_data volume
# Usage: ./scripts/upload_dataset.sh

docker exec -it be-dataapp-provider bash -c "ls data/datalake" 

docker run --rm -v "$(pwd)/dataset:/source_data" -v "trueconnector_be_dataapp_provider_data:/target_data" alpine sh -c "cp -r /source_data/* /target_data/datalake/"

docker exec -it be-dataapp-provider bash -c "ls data/datalake" 
docker exec -it be-dataapp-consumer bash -c "ls data/datalake"