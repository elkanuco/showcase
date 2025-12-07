#!/bin/bash
set -e
# docker exec -it centos-container bash
echo "========================================="
echo "Setting up CentOS Development Container"
echo "========================================="

# Update system
cd /etc/yum.repos.d/

# Backup the old repo files (just in case)
sed -i 's/^/#/' *.repo

# Create a new vault repo file
cat > centos7-vault.repo << 'EOF'
[base]
name=CentOS-7 - Base
baseurl=http://vault.centos.org/centos/7/os/x86_64/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
enabled=1

[updates]
name=CentOS-7 - Updates
baseurl=http://vault.centos.org/centos/7/updates/x86_64/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
enabled=1

[extras]
name=CentOS-7 - Extras
baseurl=http://vault.centos.org/centos/7/extras/x86_64/
gpgcheck=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
enabled=1
EOF

# Clear yum cache and test
yum clean all && yum makecache && yum update -y

# Install development tools
yum install -y \
    maven \
    git \
    wget \
    curl \
    vim \
    createrepo \
    rpm-build \
    rpmdevtools \
    wget \
    git \

cd /opt

wget https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.10%2B7/OpenJDK17U-jdk_x64_linux_hotspot_17.0.10_7.tar.gz
tar xzf OpenJDK17U-jdk_x64_linux_hotspot_17.0.10_7.tar.gz
ln -s jdk-17.0.10+7 java


alternatives --install /usr/bin/java java /opt/java/bin/java 1
alternatives --set java /opt/java/bin/java

# Install Maven 3.8+ (CentOS 7 has old Maven)

wget https://dlcdn.apache.org/maven/maven-3/3.9.11/binaries/apache-maven-3.9.11-bin.tar.gz
tar xzf apache-maven-3.9.11-bin.tar.gz
ln -s apache-maven-3.9.11 maven

# Set environment variables
cat >> /root/.bashrc << 'EOF'
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
export M2_HOME=/opt/maven
export PATH=$M2_HOME/bin:$PATH
export PATH=$JAVA_HOME/bin:$PATH
export NEXUS_URL=http://nexus:8081
export GITLAB_URL=http://gitlab.elkanuco.lu:8880
EOF

source /root/.bashrc

# Configure Git
git config --global user.name "DevOps User"
git config --global user.email "devops@example.com"
git config --global http.sslVerify false

# Add hosts entries
echo "127.0.0.1 gitlab.elkanuco.lu" >> /etc/hosts

echo "========================================="
echo "Setup completed!"
echo "Maven version: $(mvn -version)"
echo "Java version: $(java -version)"
echo "========================================="