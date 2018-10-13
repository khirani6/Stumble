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
const common_1 = require("@nestjs/common");
const create_recruiter_dto_1 = require("./dto/create-recruiter.dto");
const recruiters_service_1 = require("./recruiters.service");
const swagger_1 = require("@nestjs/swagger");
let RecruitersController = class RecruitersController {
    constructor(recruitersService) {
        this.recruitersService = recruitersService;
    }
    async create(CreateRecruiterDto) {
        this.recruitersService.create(CreateRecruiterDto);
    }
    async findAll() {
        return this.recruitersService.findAll();
    }
    async getRecruiter(id) {
        return await this.recruitersService.findById(id);
    }
    async getRecruiterEid(eid) {
        return await this.recruitersService.find(eid);
    }
};
__decorate([
    common_1.Post('create'),
    swagger_1.ApiOperation({ title: 'Create a Recruiter entry' }),
    swagger_1.ApiResponse({
        status: 201,
        description: 'The record has been successfully created.',
    }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Body()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [create_recruiter_dto_1.CreateRecruiterDto]),
    __metadata("design:returntype", Promise)
], RecruitersController.prototype, "create", null);
__decorate([
    common_1.Get('getall'),
    swagger_1.ApiOperation({ title: 'Gets all Recruiters and returns them in an array' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], RecruitersController.prototype, "findAll", null);
__decorate([
    common_1.Get('/:id'),
    swagger_1.ApiOperation({ title: 'Gets a Recruiter by id and returns them as a JSON' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Param('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], RecruitersController.prototype, "getRecruiter", null);
__decorate([
    common_1.Get('/eid/:eid'),
    swagger_1.ApiOperation({ title: 'Gets a Recruiter by eid and returns them as a JSON' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Param('eid')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], RecruitersController.prototype, "getRecruiterEid", null);
RecruitersController = __decorate([
    swagger_1.ApiUseTags('Recruiter'),
    common_1.Controller('recruiter'),
    __metadata("design:paramtypes", [recruiters_service_1.RecruitersService])
], RecruitersController);
exports.RecruitersController = RecruitersController;
//# sourceMappingURL=recruiters.controller.js.map