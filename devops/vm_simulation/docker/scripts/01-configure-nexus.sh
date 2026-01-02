#!/bin/bash
set -e

NEXUS_URL="http://nexus:8081"
NEXUS_USER="admin"
NEXUS_PASSWORD="secret"  


echo "========================================="
echo "Manual Nexus Configuration Required:"
echo "========================================="
echo "1. Access Nexus at: http://localhost:8081"
echo "2. Login with admin / (get password from container)"
echo "   docker exec nexus cat /nexus-data/admin.password"
echo "3. Change admin password to: admin123"
echo "4. Enable anonymous access"
echo ""
echo "5. Create these repositories:"
echo "   - maven-releases (maven2 hosted)"
echo "   - maven-snapshots (maven2 hosted)"
echo "   - rpm-releases (raw hosted)"
echo ""
echo "6. Create deployment user:"
echo "   Username: deployment"
echo "   Password: deployment123"
echo "   Role: nx-admin"
echo "========================================="
echo "========================================="
read -p "Press enter config is done..."

