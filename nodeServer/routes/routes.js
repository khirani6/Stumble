const UsersController = require('../controllers/user_controller');

module.exports = (app)  => {

    //Watch for incoming request of method GET
    // to the route http://localhost:3050/api
    app.get('/api', UsersController.greeting);

    app.post('/user/create', UsersController.create);

    app.post('/user/findbyemail', UsersController.getUserByEmail)
};