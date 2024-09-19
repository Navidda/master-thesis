#!/bin/bash
# This script copies the dataset from the dataset folder to the dataapp_provider_data volume
# Usage: ./scripts/upload_dataset.sh

echo "Before copying the dataset (provider):"
docker exec -it be-dataapp-provider bash -c "ls data/datalake" 

echo "Copying dataset to the dataapp_provider_data volume"
docker run --rm -v "$(pwd)/dataset:/source_data" -v "trueconnector_be_dataapp_provider_data:/target_data" alpine sh -c "cp -r /source_data/* /target_data/datalake/"

echo "After copying the dataset (provider):"
docker exec -it be-dataapp-provider bash -c "ls data/datalake" 

echo "After copying the dataset (consumer):"
docker exec -it be-dataapp-consumer bash -c "ls data/datalake"