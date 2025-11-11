# Change-aware integration system


> [!WARNING]
>
> <b>Discloser</b>
>
> - *THERE MIGTH BE A LOT OF REDUNDANCY IN THE DESIGN*
> - *THIS IS JUST A POC*
>   - to every problem there are potentially multiple solutions your solution might be different ^^
> - *NOT INTENDEND TO BE PERFECT*
>   - This is a showcase integrating a set of tools rendering a common outcome
>     - en event-based approach at different levels
> - *NOT FULLY TESTED*

</br>

> [!NOTE]
>
> What is this about?</br>
> The synchronization of changes from external sources is quite a common topic.</br>
> Having an event-based mechanism, streaming these changes between systems is a popular decision.</br>
> Building an intermediate service, caching these changes is also a smart decision to decouple internal and external services.</br></br>
> Why is this important?</br>
> We need to find a way of bridging "change deltas" between systems dealing with real-time syncing and heartbeat-like updates.</br></br>
> How does this work?</br>
> Basically a central hub for caching using Redis via an event-based mechanism using Kafka and streaming updates using gRpc as oposed of REST overs HTTP to leverage efficient, low-latency, bidirectional streaming, compact binary serialization via Protocol Buffers, and stronger contract-based APIs for high-performance communication.</br></br>
> What happens if we implement this?</br>
> A client front end will be more reactive to changes.
> Integrating a service in between producer and consumer add extra level of complexity but also adds flexibility and better separation of concerns.

## Project structure

``common`` is a shared library published in a local ``nexus``. This library will hold the DTOs used to create the Kafka events.</br>
``thirdparty`` is an external ``Spring Boot`` app responsible for managing project and people data.</br>
This application should connect to Kafka and produce the events whenever there is an update. Always sending the entire full structure per project (for now) </br>
``deltaBridge``, another ``Spring Boot``, should connect to Kafka and consume the events and store the data on ``redis``</br>
Once deployed the application should load app data per projects in the cache and stream changes via ``websockets`` towards a client front end.</br>
``frontend`` will be a ``Vue`` basic application build using ``vuetify``, using layouts with side panel, header and main content, with auto router loading different pages and streaming changes with ``websockets``.</br>

## Requirements

- Docker
- DBeaver or anything else to inspect the database and get the ids for the HTTP POST
- Inspect the containers when deploying you might need to retry

## How to deploy

- Inspect ``setup.sh``
- Update using the Swagger API : <http://localhost:8083/swagger-ui/index.html#/project-assignment-controller/addPersonToProject>
- Inspect the kafka events: <http://localhost:8085/ui>
- Inspect Redis: <http://localhost:8087>
- Access the front end: <http://localhost:8089>

## Improvements

- handle partial updates
- access wheter or not some services can be merged
- improve page
- try gRpc ?

## TL;DR

| [DB](./docs/DB.md)|[websockets](./docs/websockets.md)|[kafka](./docs/kafka.md)|[redis](./docs/redis.md)
|--------|--------|--------|--------
