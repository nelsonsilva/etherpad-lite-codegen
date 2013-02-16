#!/bin/bash
echo "Building swagger-codegen ..."
cd swagger-codegen
mvn install -DskipTests=true
cd ..
echo "Building etherpad-client ..."
cd etherpad-java
mkdir -p dist
for version in $(<../VERSIONS)
do
    export URL=http://localhost:9001
    export API_VERSION=$version
    export API_KEY=YOURAPIKEY
    mvn clean package
    cp target/etherpad-client-$version.jar dist
done
cd ..
