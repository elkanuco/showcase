#!/bin/bash
set -e

echo "========================================"
echo "Waiting for Nexus to be healthy..."
echo "========================================"

echo "Waiting for Nexus to be fully ready..."
until [ "$(curl -s -o /dev/null -w '%{http_code}' http://nexus:8081/service/rest/v1/status)" = "200" ]; do
    echo "Nexus not ready yet... sleeping 10s"
    sleep 10
done

echo "Nexus is UP! Proceeding with Maven build..."
echo "Wait for the common jar deployment"
sleep 120

mvn clean package -DskipTests -s /app/settings.xml
cp /app/target/*.jar app.jar
java -jar app.jar