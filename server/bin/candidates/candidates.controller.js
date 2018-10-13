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
const create_candidate_dto_1 = require("./dto/create-candidate.dto");
const candidates_service_1 = require("./candidates.service");
const swagger_1 = require("@nestjs/swagger");
let CandidatesController = class CandidatesController {
    constructor(candidatesService) {
        this.candidatesService = candidatesService;
    }
    async create(createCandidateDto) {
        this.candidatesService.create(createCandidateDto);
    }
    async findAll() {
        return this.candidatesService.findAll();
    }
    async getCandidate(id) {
        return await this.candidatesService.findById(id);
    }
    async updateCandidate(id, body) {
        return await this.candidatesService.update(id, body);
    }
    async emailCandidate(id) {
        return await this.candidatesService.emailCandidate(id);
    }
    async getCandidateByEvent(id) {
        return await this.candidatesService.findByEvent(id);
    }
    async getCandidateByRecruiter(id) {
        return await this.candidatesService.findByEvent(id);
    }
};
__decorate([
    common_1.Post('create'),
    swagger_1.ApiOperation({ title: 'Create a Candidate entry' }),
    swagger_1.ApiResponse({
        status: 201,
        description: 'The record has been successfully created.',
    }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Body()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [create_candidate_dto_1.CreateCandidateDto]),
    __metadata("design:returntype", Promise)
], CandidatesController.prototype, "create", null);
__decorate([
    common_1.Get('getall'),
    swagger_1.ApiOperation({ title: 'Gets all Candidates and returns them in an array' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], CandidatesController.prototype, "findAll", null);
__decorate([
    common_1.Get('/:id'),
    swagger_1.ApiOperation({ title: 'Gets a Candidate by id and returns them as a JSON' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Param('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], CandidatesController.prototype, "getCandidate", null);
__decorate([
    common_1.Patch('/:id'),
    swagger_1.ApiOperation({ title: 'Updates a Candidate by id' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Param('id')), __param(1, common_1.Body()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, Object]),
    __metadata("design:returntype", Promise)
], CandidatesController.prototype, "updateCandidate", null);
__decorate([
    common_1.Get('email/:id'),
    swagger_1.ApiOperation({ title: 'Sends email' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Param('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], CandidatesController.prototype, "emailCandidate", null);
__decorate([
    common_1.Get('/event/:id'),
    swagger_1.ApiOperation({ title: 'Gets a Candidate by event id and returns them as a JSON' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Param('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], CandidatesController.prototype, "getCandidateByEvent", null);
__decorate([
    common_1.Get('/recruiter/:id'),
    swagger_1.ApiOperation({ title: 'Gets a Candidate by recruiter id and returns them as a JSON' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Param('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], CandidatesController.prototype, "getCandidateByRecruiter", null);
CandidatesController = __decorate([
    swagger_1.ApiUseTags('Candidate'),
    common_1.Controller('candidate'),
    __metadata("design:paramtypes", [candidates_service_1.CandidatesService])
], CandidatesController);
exports.CandidatesController = CandidatesController;
//# sourceMappingURL=candidates.controller.js.map