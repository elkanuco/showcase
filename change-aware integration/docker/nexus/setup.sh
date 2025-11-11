#!/bin/bash
set -e

until [ -f /nexus-data/admin.password ]; do
    sleep 5;
done

echo "Waiting for Nexus to be fully ready..."
until [ "$(curl -s -o /dev/null -w '%{http_code}' http://localhost:8081/service/rest/v1/status)" = "200" ]; do
    sleep 5
done

OLD_PASSWORD=$(cat /nexus-data/admin.password)
NEW_PASSWORD="secret"
USERNAME="admin"

echo "========================================"
echo "NEXUS SETUP START!"
echo "========================================"

echo "Changing admin password..."

curl -s -o /dev/null -w "HTTP: %{http_code}\n" \
  -X PUT \
  -H "Content-Type: text/plain" \
  -u "${USERNAME}:${OLD_PASSWORD}" \
  --data "${NEW_PASSWORD}" \
  "http://localhost:8081/service/rest/v1/security/users/${USERNAME}/change-password"

echo "Admin password successfully changed to: $NEW_PASSWORD"

curl -s -o /dev/null -w "Realms: %{http_code}\n" \
  -u "${USERNAME}:${NEW_PASSWORD}" -X PUT \
  "http://localhost:8081/service/rest/v1/security/realms/active" \
  -H "Content-Type: application/json" \
  -d '["NexusAuthenticatingRealm"]'

curl -s -o /dev/null -w "Anonymous: %{http_code}\n" \
  -u "${USERNAME}:${NEW_PASSWORD}" -X PUT \
  "http://localhost:8081/service/rest/v1/security/anonymous" \
  -H "Content-Type: application/json" \
  -d '{"enabled": true, "userId": "anonymous", "realmName": "NexusAuthenticatingRealm"}'

curl -s -o /dev/null -w "Anonymous Update: %{http_code}\n" \
  -u "${USERNAME}:${NEW_PASSWORD}" -X PUT \
  "http://localhost:8081/service/rest/v1/security/users/anonymous" \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "anonymous",
    "firstName": "Anonymous",
    "lastName": "User",
    "emailAddress": "anonymous@example.com",
    "source": "default",
    "status": "active",
    "roles": ["nx-anonymous", "nx-admin"]
  }'

sleep 10

EULA_RESPONSE=$(curl -u "${USERNAME}:${NEW_PASSWORD}" -X GET 'http://localhost:8081/service/rest/v1/system/eula' -H 'accept: application/json' -H 'X-Nexus-UI: true')
DISCLAIMER=$(echo "$EULA_RESPONSE" | sed -n 's/.*"disclaimer" *: *"\([^"]*\)".*/\1/p')
echo "$DISCLAIMER"
curl -u "${USERNAME}:${NEW_PASSWORD}" -X POST 'http://localhost:8081/service/rest/v1/system/eula' \
  -H "Content-Type: application/json" \
  -d "{\"disclaimer\":\"${DISCLAIMER}\",\"accepted\":true}"

echo "========================================"
echo "NEXUS SETUP COMPLETE!"
echo "========================================"
