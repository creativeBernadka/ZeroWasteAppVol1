const http = require('http');
const app = require('./api/services/app');
const port = process.env.NODE_PORT || 3000;

const server = http
    .createServer(app)
    .listen({   // dajemy port w {} zeby byl traktowany jako obiekt
        port
    }, () => {
        console.log("Node server started at", port)
    });

module.exports = server;

// const {placesRoute} = require('./api/routes/placesRoute');
//
// const express = require('express'),
//     app = express(),
//     port = process.env.PORT || 3000;
//
// app.listen(port);
// app.use(placesRoute);
//
// console.log('todo list RESTful API server started on: ' + port);