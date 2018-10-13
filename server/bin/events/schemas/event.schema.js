"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const mongoose = require("mongoose");
exports.EventSchema = new mongoose.Schema({
    name: String,
    startDate: Date,
    endDate: Date,
    automatedEmailContent: Array()
});
//# sourceMappingURL=event.schema.js.map