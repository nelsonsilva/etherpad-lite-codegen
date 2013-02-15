#!/bin/sh
cd swagger-codegen
mvn install -DskipTests=true
cd ..
cd etherpad-java
mvn install
