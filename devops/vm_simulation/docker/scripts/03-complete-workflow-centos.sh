#!/bin/bash
set -e

echo "========================================="
echo "Complete CI/CD Workflow"
echo "========================================="



echo "========================================="
echo "Manual Steps Required:"
echo "========================================="
echo "docker exec -it gitlab cat /etc/gitlab/initial_root_password"
echo "1. Access GitLab at: http://localhost:8880"
echo "2. Login with username: root"
echo "   Password: GilWWr7hzIFT8BGCB0VZ+Hl6J2nJGimpZQQPM2K9eeA="
echo "3. Create a new project: spring-boot-app"
echo "4. Get the clone URL"
echo ""
read -p "Press enter after creating the GitLab project..."

#HOST ONLY GITLAB_PASSWORD=$(docker exec -it gitlab cat /etc/gitlab/initial_root_password|grep Password|awk -F': ' '{print $2}')
#HOST ONLY ENCODED_GITLAB_PASSWORD=$(python3 -c "import urllib.parse, sys; print(urllib.parse.quote(sys.argv[1]))" "$GITLAB_PASSWORD")
#HOST ONLY echo "$ENCODED_GITLAB_PASSORD"
#HOST ONLY git remote add origin http://root:${ENCODED_GITLAB_PASSWORD}@gitlab:80/root/workspace.git

# Initialize Git repository
cd /data/workspace
git init
git remote add origin http://root:blCb%2BU9x%2BC5GsaA3Uvkb7ObuZPdMHTFbxsgxc%2FQdJLA%3D@gitlab:80/root/workspace.git
#git remote rm origin
#git remote set-url origin

# Add files
git add .
git commit -m "Initial commit: Spring Boot application with RPM packaging"
git branch -M develop
#git pull
#git rebase origin/develop
git push -u origin develop

echo "========================================="
echo "Code pushed to GitLab!"
echo "========================================="

# Build 
mvn clean install -s ../settings.xml

#Release
mvn release:clean release:prepare release:perform -DreleaseVersion=1.0.0 -DdevelopmentVersion=2.0.0-SNAPSHOT -Dtag=v1.0.0 -s settings.xml
#mvn release:rollback -s settings.xml

# Build RPM package
cd ../deployment
mvn clean install -Dapp.jar.version=1.0.0 -Prpm -s ../settings.xml 

# Upload RPM to Nexus
echo "Uploading RPM to Nexus..."
RPM_FILE=$(find target/rpm -name "*.rpm" | head -n 1)
# e.g. : target/rpm/spring-boot-app/RPMS/noarch/spring-boot-app-0.0.1-SNAPSHOT20251207142143.noarch.rpm

RPM_FILENAME=$(basename "$RPM_FILE")
echo "Found RPM: $RPM_FILENAME"

# Create temporary directory for repository
TEMP_REPO=$(mktemp -d)
echo "Creating repository in: $TEMP_REPO"

# Copy RPM to temp directory
cp "$RPM_FILE" "$TEMP_REPO/"

# Create repository metadata
cd "$TEMP_REPO"


# THIS IS CRITICAL - Download ALL existing RPMs
curl -s -u deployment:deployment123 \
    "http://nexus:8081/service/rest/v1/components?repository=rpm-releases" | \
    grep -o '"downloadUrl" : "[^"]*\.rpm"' | \
    sed 's/"downloadUrl" : "//;s/"//' | \
    while read url; do
        filename=$(basename "$url")
        echo "  Downloading: $filename"
        wget -q --user=deployment --password=deployment123 "$url"
    done





echo "Creating repository metadata..."
createrepo .
#see createrepo --update .

# Upload RPM file to root of repository
echo "Uploading RPM file..."
curl -v -u deployment:deployment123 --upload-file ${RPM_FILENAME} http://nexus:8081/repository/rpm-releases/


# Upload repodata files to root of repository
echo "Uploading repository metadata..."
cd repodata
for file in *; do
    echo "  Uploading: repodata/$file"
    curl -u deployment:deployment123 \
        --upload-file "$file" \
        "http://nexus:8081/repository/rpm-releases/repodata/$file"
done

cd /
rm -rf "$TEMP_REPO"

echo "========================================="
echo "RPM package deployed to Nexus!"
echo "========================================="

# Configure YUM and install


yum clean all && yum makecache && yum list available spring-boot-app --showduplicates

export DB_PASSWORD=password

sudo tee /etc/default/spring-boot-app <<EOF
DB_PASSWORD=password
EOF
chown -R springboot:springboot /etc/default/spring-boot-app
chmod -R u+rwX,go+rX /etc/default/spring-boot-app

# Install the RPM
echo "Installing RPM package..."
yum install -y spring-boot-app
# yum autoremove -y spring-boot-app


#chown -R springboot:springboot /opt/spring-boot-app/
#chmod -R u+rwX,go+rX /opt/spring-boot-app/
#chmod 644 /opt/spring-boot-app/config/application.properties
#ls -la /opt/spring-boot-app/config/

#docker inspect gitlab-runner | grep -A 10 Networks
docker exec -it gitlab-runner bash

gitlab-runner register \
  --non-interactive \
  --url "http://gitlab:80" \
  --registration-token "GR1348941d5WXgLPnHxcKLhJ2d1sj" \
  --executor "docker" \
  --docker-image "alpine:latest" \
  --description "docker-runner" \
  --docker-network-mode "docker_default" \
  --run-untagged="true" \
  --locked="false"

echo "========================================="
echo "Installation completed!"
echo "========================================="
echo "Configure: /opt/spring-boot-app/config/application.properties"
echo "Start: systemctl start spring-boot-app"
echo "========================================="