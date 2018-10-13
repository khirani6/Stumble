"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const mongoose = require("mongoose");
exports.CandidateSchema = new mongoose.Schema({
    firstName: {
        type: String,
        required: true
    },
    lastName: {
        type: String,
        required: true,
    },
    email: {
        type: String,
        required: true
    },
    position: {
        type: String,
        required: false
    },
    gradDate: {
        type: String,
        required: false
    },
    rating: {
        type: Number,
        required: false
    },
    recruiterId: {
        type: mongoose.Schema.Types.ObjectId,
        required: false,
        ref: 'Recruiter'
    },
    notes: {
        type: String,
        required: false
    },
    areaOfInterest: {
        type: Array(),
        required: false
    },
    event: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Event',
        required: true
    }
});
//# sourceMappingURL=candidate.schema.js.map