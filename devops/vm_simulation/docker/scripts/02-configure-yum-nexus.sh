#!/bin/bash
set -e

NEXUS_URL="http://nexus:8081"
NEXUS_USER="deployment"
NEXUS_PASSWORD="deployment123"

echo "Configuring YUM to use Nexus repository..."

# Create Nexus repo file
cat > /etc/yum.repos.d/nexus.repo << EOF
[nexus-rpm]
name=Nexus RPM Repository
baseurl=${NEXUS_URL}/repository/rpm-releases/
enabled=1
gpgcheck=0
priority=1
EOF

# Test connection
echo "Testing Nexus connection..."
yum repolist

echo "YUM configured to use Nexus!"