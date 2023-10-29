#!/bin/bash

gradle -p backend/ bootJar
sudo docker compose build
if [ "$1" == "--prod" ]; then
  sudo docker compose --profile prod up -d
else
  sudo docker compose up
fi
