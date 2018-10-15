const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const routes = require('./routes/routes');

const app = express();

mongoose.connect('mongodb://dtempleton:qwerty123@ds255797.mlab.com:55797/stumble');

//sets bodyParser up to collect body as json.
//WARNING bodyParser MUST come before routes(app) in order to have Json
app.use(bodyParser.json());
routes(app);

app.use((err, req, res, next) => {
    res.status(422).send({error: err.message});
});

module.exports = app;