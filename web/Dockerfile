FROM node:lts

EXPOSE 3000

WORKDIR /web_app

COPY ./*.json ./*.lock ./

RUN yarn install

COPY . ./

ENTRYPOINT ["yarn", "start"]