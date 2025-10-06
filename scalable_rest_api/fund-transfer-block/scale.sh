#!/bin/bash -x 
source ./configrc
docker compose up --scale fund-transfer-service=3 -d