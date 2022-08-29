FROM node:lts-alpine3.12
WORKDIR ./gate-simulator
COPY . .
##COPY package*.json ./
RUN npm install
##COPY . .
EXPOSE 9999
CMD ["npm", "start"]