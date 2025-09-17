# bash shell + trace mode
#!/bin/bash -x 
# Exits the script immediately if any simple command returns a non-zero (error) status.
set -e
# Changes the exit status of pipelines so that if any command 
# within a pipeline fails, the entire pipeline returns that failure status.
set -o pipefail
# setup variables
source ./configrc
# deploy
docker-compose up --build -d