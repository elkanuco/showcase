#!/bin/bash

cp -r --force ../common ./common/
cp -r --force ../thirdparty ./thirdparty/
cp -r --force ../deltabridge ./deltabridge/
cp -r --force ../vuetify ./vuetify/

docker compose up --build