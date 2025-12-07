#!/bin/bash
set -e

echo "========================================="
echo "Complete CI/CD Workflow"
echo "========================================="

cd /data/workspace


echo "========================================="
echo "Manual Steps Required:"
echo "========================================="
echo "docker exec -it gitlab cat /etc/gitlab/initial_root_password"
echo "1. Access GitLab at: http://localhost:8880"
echo "2. Login with username: root"
echo "   Password: JOmsSWykDbFExvjc2bs84+JDLYZ5RF65CSefbym9Eu0="
echo "3. Create a new project: spring-boot-app"
echo "4. Get the clone URL"
echo ""
read -p "Press enter after creating the GitLab project..."

# Initialize Git repository
cd spring-boot-app
git init
git remote add origin http://root:JOmsSWykDbFExvjc2bs84+JDLYZ5RF65CSefbym9Eu0=@gitlab.local:8880/root/spring-boot-app.git

# Add files
git add .
git commit -m "Initial commit: Spring Boot application with RPM packaging"
git branch -M main
git pull
git rebase origin/main
git push -u origin main

echo "========================================="
echo "Code pushed to GitLab!"
echo "========================================="

# Build and deploy to Nexus
echo "Building and deploying to Nexus..."
cd spring-boot-app
mvn clean install deploy -s ../settings.xml

# Build RPM package
echo "Building RPM package..."
cd ../deployment
mvn clean package -s ../settings.xml

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
    grep -o '"downloadUrl":"[^"]*\.rpm"' | \
    sed 's/"downloadUrl":"//;s/"//' | \
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


yum clean all && yum makecache && yum list available spring-boot-app

export DB_PASSWORD=password

# Install the RPM
echo "Installing RPM package..."
yum install -y spring-boot-app
# yum autoremove -y spring-boot-app

echo "========================================="
echo "Installation completed!"
echo "========================================="
echo "Configure: /opt/spring-boot-app/config/application.properties"
echo "Start: systemctl start spring-boot-app"
echo "========================================="