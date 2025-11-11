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

echo "Wait a bit"
sleep 15

# Build and package
mvn clean package -DskipTests -s /app/settings.xml

# Deploy artifact
mvn deploy:deploy-file \
  -s /app/settings.xml \
  -Durl=http://nexus:8081/repository/maven-releases/ \
  -DrepositoryId=nexus-releases \
  -Dfile=target/common-0.0.1.jar \
  -DpomFile=pom.xml

echo "Build and deployment complete!"