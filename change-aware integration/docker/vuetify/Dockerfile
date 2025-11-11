FROM node:22-alpine as build-stage
WORKDIR /app
RUN apk add --no-cache python3 make g++
COPY ./vuetify/package*.json ./
RUN npm install
COPY ./vuetify /app
RUN npm run build
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]