#!/bin/bash
set -e
read -p "Skip this, see 06 ..."


NEXUS_URL="http://nexus:8081"
NEXUS_HOST="nexus"
NEXUS_USER="deployment"
NEXUS_PASSWORD="deployment123"

DIST="jammy"          
COMPONENT="main"      

echo "Configuring APT to use Nexus repository..."

cat > /etc/apt/auth.conf.d/nexus.conf << EOF
machine ${NEXUS_HOST}
login ${NEXUS_USER}
password ${NEXUS_PASSWORD}
EOF
chmod 600 /etc/apt/auth.conf.d/nexus.conf

cat > /etc/apt/sources.list.d/nexus.list << EOF
deb [trusted=yes] ${NEXUS_URL}/repository/apt-hosted/ ${DIST} ${COMPONENT}
EOF

apt-get update

echo "APT configured to use Nexus!"