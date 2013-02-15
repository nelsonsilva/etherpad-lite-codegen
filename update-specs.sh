#!/bin/sh
mkdir -p specs/api
wget http://localhost:9001/rest/api -O specs/resources.json
for resource in "pad" "session" "author" "group"
do
  wget http://localhost:9001/rest/api/$resource -O specs/api/$resource
done
