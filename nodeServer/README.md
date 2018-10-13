
## Structure of the Project
Inside of the `server` folder is a Nest.JS application written in Typescript. It is responsible for providing APIs and serving the static assets from the client once deployed.

## Installation
The project has been setup so that everything can be installed and ran using the root directory's `package.json`.

To install run `npm install` in the root directory. This will install the required packages for running both the client and the server as well as server's node modules.

## Development
Development is easy, simply run `npm start` from the root directory. This will start server's dev servers. If ever a backend change is made, you may have to reload the client page manually to make a new API request.