#!/bin/bash
set -e

echo "========================================="
echo "Complete CI/CD Workflow - Ubuntu/Debian"
echo "========================================="

cd /data/workspace

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

# Initialize Git repository
git init
git remote add origin http://root:GilWWr7hzIFT8BGCB0VZ+Hl6J2nJGimpZQQPM2K9eeA=@gitlab:80/root/workspace.git
#git remote rm origin

# Add files
git add .
git commit -m "Initial commit: Spring Boot application with Debian packaging"
git branch -M main
#git pull
#git rebase origin/main
git push -u origin main

echo "========================================="
echo "Code pushed to GitLab!"
echo "========================================="

# Build and deploy to Nexus
echo "Building and deploying to Nexus..."
cd spring-boot-app
mvn clean install deploy -s ../settings.xml

# Build Debian package
echo "Building Debian package..."
cd ../deployment
mvn clean package -Pdebian -s ../settings.xml

# Upload DEB to Nexus
echo "Uploading DEB to Nexus..."
DEB_FILE=$(find target -name "*.deb" | head -n 1)
# e.g.: target/spring-boot-app_0.0.1-SNAPSHOT_all.deb

DEB_FILENAME=$(basename "$DEB_FILE")
echo "Found DEB: $DEB_FILENAME"

# Create temporary directory for repository
TEMP_REPO=$(mktemp -d)
echo "Creating repository in: $TEMP_REPO"

# Copy DEB to temp directory
cp "$DEB_FILE" "$TEMP_REPO/"

# Create Debian repository structure
cd "$TEMP_REPO"
mkdir -p pool/main conf

# Download ALL existing DEBs
echo "Downloading existing packages from Nexus..."
curl -s -u deployment:deployment123 \
    "http://nexus:8081/service/rest/v1/components?repository=deb-releases/pool/main/s/spring-boot-app" | \
    grep -o '"downloadUrl":"[^"]*\.deb"' | \
    sed 's/"downloadUrl":"//;s/"//' | \
    while read url; do
        filename=$(basename "$url")
        echo "  Downloading: $filename"
        wget -q --user=deployment --password=deployment123 -P pool/main "$url"
    done

# Move new DEB to pool
mv "$DEB_FILENAME" pool/main/

# Create distributions configuration
cat > conf/distributions << 'EOF'
Origin: elkanuco
Label: elkanuco
Codename: stable
Architectures: amd64 arm64 source
Components: main
Description: Elkanuco APT Repository
EOF

# Install reprepro if not available
if ! command -v reprepro &> /dev/null; then
    echo "Installing reprepro..."
    apt-get update && apt-get install -y reprepro
fi

# Create repository metadata
echo "Creating repository metadata..."
reprepro -b . includedeb stable pool/main/*.deb

DEB_FILE_TILDE=$(find pool/main/s/spring-boot-app/ -name "*.deb" | head -n 1)

# Upload DEB file
echo "Uploading DEB file..."
curl -v -u deployment:deployment123 \
    --upload-file "pool/main/s/spring-boot-app/${DEB_FILE_TILDE}" \
    "http://nexus:8081/repository/deb-releases/pool/main/s/spring-boot-app/${DEB_FILE_TILDE}"

# Upload repository metadata files
echo "Uploading repository metadata..."
if [ -d "dists/stable" ]; then
    cd dists/stable
    
    # Upload Release file
    if [ -f "Release" ]; then
        echo "  Uploading: Release"
        curl -u deployment:deployment123 \
            --upload-file "Release" \
            "http://nexus:8081/repository/deb-releases/dists/stable/Release"
    fi
    
    # Upload Packages files
    if [ -d "main/binary-all" ]; then
        cd main/binary-all
        for file in Packages*; do
            if [ -f "$file" ]; then
                echo "  Uploading: main/binary-all/$file"
                curl -u deployment:deployment123 \
                    --upload-file "$file" \
                    "http://nexus:8081/repository/deb-releases/dists/stable/main/binary-all/$file"
            fi
        done
        cd ../..
    fi
    
    if [ -d "main/binary-amd64" ]; then
        cd main/binary-amd64
        for file in Packages*; do
            if [ -f "$file" ]; then
                echo "  Uploading: main/binary-amd64/$file"
                curl -u deployment:deployment123 \
                    --upload-file "$file" \
                    "http://nexus:8081/repository/deb-releases/dists/stable/main/binary-amd64/$file"
            fi
        done
    fi
fi

cd /
rm -rf "$TEMP_REPO"

echo "========================================="
echo "DEB package deployed to Nexus!"
echo "========================================="

# Configure APT and install
echo "Configuring APT repository..."

# Create sources list
cat > /etc/apt/sources.list.d/elkanuco.list << 'EOF'
deb [trusted=yes] http://nexus:8081/repository/deb-releases/ stable main
EOF

# Update package cache
echo "Updating package cache..."
apt-get update

# List available package
echo "Available package:"
apt-cache policy spring-boot-app

export DB_PASSWORD=password

# Install the DEB
echo "Installing DEB package..."
apt-get install -y spring-boot-app
# apt-get remove -y spring-boot-app

echo "========================================="
echo "Installation completed!"
echo "========================================="
echo "Configure: /opt/spring-boot-app/config/application.properties"
echo "Start: systemctl start spring-boot-app"
echo "Status: systemctl status spring-boot-app"
echo "Logs: journalctl -u spring-boot-app -f"
echo "========================================="