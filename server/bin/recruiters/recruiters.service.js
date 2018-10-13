"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
var _a;
const mongoose_1 = require("mongoose");
const common_1 = require("@nestjs/common");
const mongoose_2 = require("@nestjs/mongoose");
let RecruitersService = class RecruitersService {
    constructor(recruiterModel) {
        this.recruiterModel = recruiterModel;
    }
    async create(createRecruiterDto) {
        const createdRecruiter = new this.recruiterModel(createRecruiterDto);
        return await createdRecruiter.save();
    }
    async findById(id) {
        return await this.recruiterModel.findById(id).exec();
    }
    async find(eid) {
        return await this.recruiterModel.find({ eid: eid }).exec();
    }
    async findAll() {
        return await this.recruiterModel.find().exec();
    }
};
RecruitersService = __decorate([
    common_1.Injectable(),
    __param(0, mongoose_2.InjectModel('Recruiter')),
    __metadata("design:paramtypes", [typeof (_a = typeof mongoose_1.Model !== "undefined" && mongoose_1.Model) === "function" && _a || Object])
], RecruitersService);
exports.RecruitersService = RecruitersService;
//# sourceMappingURL=recruiters.service.js.map