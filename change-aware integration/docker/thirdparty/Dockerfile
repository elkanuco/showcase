FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY ./thirdparty /app
COPY settings.xml /app/settings.xml
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]