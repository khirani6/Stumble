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
const create_event_dto_1 = require("./dto/create-event.dto");
const events_service_1 = require("./events.service");
const swagger_1 = require("@nestjs/swagger");
let EventsController = class EventsController {
    constructor(eventService) {
        this.eventService = eventService;
    }
    async create(createEventDto) {
        this.eventService.create(createEventDto);
    }
    async findAll() {
        return this.eventService.findAll();
    }
    async getEvent(id) {
        return await this.eventService.findById(id);
    }
};
__decorate([
    common_1.Post('create'),
    swagger_1.ApiOperation({ title: 'Create an Event entry' }),
    swagger_1.ApiResponse({
        status: 201,
        description: 'The record has been successfully created.',
    }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Body()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [create_event_dto_1.CreateEventDto]),
    __metadata("design:returntype", Promise)
], EventsController.prototype, "create", null);
__decorate([
    common_1.Get('getall'),
    swagger_1.ApiOperation({ title: 'Gets all Events and returns them in an array' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], EventsController.prototype, "findAll", null);
__decorate([
    common_1.Get('/:id'),
    swagger_1.ApiOperation({ title: 'Gets an Event by id and returns them as a JSON' }),
    swagger_1.ApiResponse({ status: 403, description: 'Forbidden.' }),
    __param(0, common_1.Param('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], EventsController.prototype, "getEvent", null);
EventsController = __decorate([
    swagger_1.ApiUseTags('Event'),
    common_1.Controller('event'),
    __metadata("design:paramtypes", [events_service_1.EventsService])
], EventsController);
exports.EventsController = EventsController;
//# sourceMappingURL=events.controller.js.map