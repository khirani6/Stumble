"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const mongoose = require("mongoose");
exports.RecruiterSchema = new mongoose.Schema({
    firstName: String,
    lastName: String,
    email: String,
    eid: String
});
//# sourceMappingURL=recruiter.schema.js.map