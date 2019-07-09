const express = require('express');
const placesRoute = require('../routes/placesRoute');

const app = express();

app.use(placesRoute);

module.exports = app;