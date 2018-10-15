const User = require('../models/user');

module.exports = {

    greeting(req, res) {
        res.send({ hi: "there"});
    },

    create(req, res, next) {
        const userProps = req.body;
        User.create(userProps)
            .then(user => res.send(user))
            .catch(next);
    },

    getUserByEmail(req, res, next) {
        const userProps = req.body;
        User.find({email: userProps.email})
            .then(user => res.send(user))
            .catch(next);
    },
};