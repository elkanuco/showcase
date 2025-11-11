#!/bin/bash
set -e

echo "========================================"
echo "Waiting for thirdparty to be healthy..."
echo "========================================"

echo "Waiting for thirdparty to be fully ready..."
until [ "$(curl -s -o /dev/null -w '%{http_code}' http://thirdparty:8080/api/projects)" = "200" ]; do
    echo "thirdparty not ready yet... sleeping 10s"
    sleep 10
done



echo "thirdparty is UP! Proceeding with Maven build..."

mvn clean package -DskipTests -s /app/settings.xml
cp /app/target/*.jar app.jar
java -jar app.jar