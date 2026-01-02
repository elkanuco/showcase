#!/bin/bash
set -e
# docker exec -it ubuntu-container bash

echo "========================================="
echo "Setting up Ubuntu Development Container"
echo "========================================="

# Update system
apt-get update && apt-get upgrade -y

# Install development tools
apt-get install -y \
    git \
    wget \
    curl \
    vim \
    gnupg \
    software-properties-common \
    build-essential \
    dpkg-dev \
    debhelper \
    devscripts \
    fakeroot \
    lintian

# Install Java 17
cd /opt
wget https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.10%2B7/OpenJDK17U-jdk_x64_linux_hotspot_17.0.10_7.tar.gz
tar xzf OpenJDK17U-jdk_x64_linux_hotspot_17.0.10_7.tar.gz
ln -s jdk-17.0.10+7 java

# Set up alternatives for Java
update-alternatives --install /usr/bin/java java /opt/java/bin/java 1
update-alternatives --set java /opt/java/bin/java

# Install Maven 3.9
cd /opt
wget https://dlcdn.apache.org/maven/maven-3/3.9.12/binaries/apache-maven-3.9.12-bin.tar.gz
tar xzf apache-maven-3.9.12-bin.tar.gz
ln -s apache-maven-3.9.12 maven

mkdir -p /opt/tmp
export MAVEN_OPTS="-Djansi.tmpdir=/opt/tmp"
export MAVEN_OPTS="-Djansi.passthrough=true"


# Set environment variables
cat >> /root/.bashrc << 'EOF'
export JAVA_HOME=/opt/java
export M2_HOME=/opt/maven
export PATH=$M2_HOME/bin:$JAVA_HOME/bin:$PATH
export NEXUS_URL=http://nexus:8081
export GITLAB_URL=http://gitlab.elkanuco.lu:8880
EOF

source /root/.bashrc

# Configure Git
git config --global user.name "DevOps User"
git config --global user.email "devops@example.com"
git config --global http.sslVerify false
git config --global --add safe.directory /data/workspace/spring-boot-app 

# Add hosts entries
echo "127.0.0.1 gitlab.elkanuco.lu" >> /etc/hosts

# Create debian package building directory structure
mkdir -p /root/debbuild/{BUILD,DEBS,SOURCES}

echo "========================================="
echo "Setup completed!"
echo "Maven version: $(mvn -version)"
echo "Java version: $(java -version)"
echo "========================================="
echo "Debian package tools installed"
echo "Ready to build .deb packages!"
echo "========================================="