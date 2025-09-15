# Simple bank transfer application

> [!WARNING] <b>Discloser</b>
> <br> for further technical details go to : [Notes](docs/NOTES.md)<br>
>
> - *THIS IS JUST A POC*
>   - to every problem there are potentially multiple solutions your solution might be different
> - *NOT INTENDEND TO BE PERFECT*
>   - This is a showcase integrating a set of tools rendering a common outcome
>     - a scalable restful api exposing basic crud and additional <i>business-specific</i> operations
> - *NOT FULLY TESTED*
>   - Tests were added to show-case the strategy and the capability of testing
>     - The strategy is basically cover all relevant cyclomatic complexity with unit test and cover nominal e2e use cases with integration tests
>       - to avoid redundancy and overly complex tests, and respecting the idempotence of test coverage

## REQUIREMENTS

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
