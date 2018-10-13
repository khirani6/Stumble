"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
const common_1 = require("@nestjs/common");
let TableService = class TableService {
    async getIndexData() {
        return new Array({
            name: 'Alpha',
            letter: 'α',
            index: 1
        }, {
            name: 'Beta',
            letter: 'β',
            index: 2
        }, {
            name: 'Gamma',
            letter: 'γ',
            index: 3
        }, {
            name: 'Delta',
            letter: 'δ',
            index: 4
        }, {
            name: 'Epsilon',
            letter: 'ε',
            index: 5
        });
    }
    async getDetails() {
        return new Array({
            name: 'Alpha',
            letter: 'α',
            index: 1
        }, {
            name: 'Beta',
            letter: 'β',
            index: 2
        }, {
            name: 'Gamma',
            letter: 'γ',
            index: 3
        }, {
            name: 'Delta',
            letter: 'δ',
            index: 4
        }, {
            name: 'Epsilon',
            letter: 'ε',
            index: 5
        }, {
            name: 'Zeta',
            letter: 'ζ',
            index: 6
        }, {
            name: 'Eta',
            letter: 'η',
            index: 7
        }, {
            name: 'Theta',
            letter: 'θ',
            index: 8
        }, {
            name: 'Iota',
            letter: 'ι',
            index: 9
        }, {
            name: 'Kappa',
            letter: 'κ',
            index: 10
        });
    }
};
TableService = __decorate([
    common_1.Injectable()
], TableService);
exports.TableService = TableService;
//# sourceMappingURL=TableService.js.map