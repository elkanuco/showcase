# REQUIREMENTS

- Git Bash or WSL or any other Unix-like terminal to run 'setup.sh' script
- Docker Desktop
- Read/View the [Notes](docs/NOTES.md), [DB model](docs/DB_MODEL.md), and [Dummy Data](docs/DUMMY_DATA_DESCRIPTION.md) files
- Inspect contents

## HOW TO DEPLOY

- Use Git Bash or WSL or any other Unix-like terminal
- Navigate to the folder where you cloned the repo
- Inspect folders
  - inside the folder 'fund-transfer-block' you will find a setup.sh script
    - Execute './setup.sh' to deploy the Docker Compose cluster
    - Execute './scale.sh' to scale fund-transfer-service up to 3 nodes
- Access the HAL BROWSER and browse through the dummy data to familiarize yourself with the data and model
  - <http://fundtransfer.localhost/datarestapi/explorer/index.html#uri=/datarestapi>
- Access the Swagger UI and familiarize yourself with the REST API
  - <http://fundtransfer.localhost/swagger-ui/index.html?urls.primaryName=operations>
    - Play with the different requests
      - 'OPERATIONS' is the default definition
        - Used to perform the WITHDRAWAL, DEPOSIT and TRANSFER operations
      - 'BASIC-CRUD' definition can be used to access the basic CRUD API on the different entities
        - Can be used to set up additional data
      - 'DATA-REST-API' definition was built by SPRING REST DATA, and the FK links are URI strings
        - e.g: <http://fundtransfer.localhost/datarestapi/accounts/1>
- Access Traefik UI to inspect nodes
  - http://localhost:8080/dashboard/
