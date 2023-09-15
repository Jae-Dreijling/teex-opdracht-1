FROM node:alpine
WORKDIR /pizza
EXPOSE 3000
COPY . .
RUN npm i
# --host option is needed to make it work on Docker Desktop on WSL
CMD npm run start
