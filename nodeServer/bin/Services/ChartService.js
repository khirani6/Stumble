"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
const common_1 = require("@nestjs/common");
let ChartService = class ChartService {
    async getChartData() {
        return new Array({
            name: 'High',
            data: [51, 54, 62, 71, 79, 86, 87, 86, 82, 72, 61, 52],
            color: '#e01616'
        }, {
            name: 'Low',
            data: [35, 37, 43, 51, 60, 67, 70, 69, 64, 54, 43, 37],
            color: '#1e87d8'
        });
    }
    async getDetailsData() {
        return new Array({
            name: 'New',
            data: [199999, 179999, 149999, 149999, 149999, 199999, 219999, 199999, 149999],
            color: ''
        }, {
            name: 'User',
            data: [110000, 90000, 90000, 90000, 105000, 130000, 115000, 90000],
            color: ''
        });
    }
};
ChartService = __decorate([
    common_1.Injectable()
], ChartService);
exports.ChartService = ChartService;
//# sourceMappingURL=ChartService.js.map