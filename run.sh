#!/bin/bash

gradle -p backend/ bootJar
sudo docker compose build

if [ "$1" == "--prod" ]; then
  if [ "$2" == "--hard-reset" ]; then
    sudo rm -rf /var/lib/docker/volumes/wg-bier-manager_mongodb_data_container
  fi
  sudo docker compose --profile prod up --build -d
else
  if [ "$2" == "--hard-reset" ]; then
    sudo rm -rf /var/lib/docker/volumes/wg-bier-manager_mongodb_data_container
  fi
  sudo docker compose --profile local up --build
fi
