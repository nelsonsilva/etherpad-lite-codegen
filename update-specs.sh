#!/bin/bash
for version in $(<VERSIONS)
do
    mkdir -p specs/$version/api
    wget http://localhost:9001/rest/$version/api -O specs/$version/resources.json
    for resource in "pad" "session" "author" "group"
    do
        wget http://localhost:9001/rest/$version/api/$resource -O specs/$version/api/$resource
    done
done
