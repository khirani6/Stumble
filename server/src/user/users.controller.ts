import { Controller, Get, Post, Body, Param, Patch } from '@nestjs/common';
import { CreateUserDto } from './dto/create-user.dto';
import { UsersService } from './users.service';
import { User } from './interfaces/user.interface';
import {
  ApiUseTags,
  ApiResponse,
  ApiOperation,
} from '@nestjs/swagger';

@ApiUseTags('User')
@Controller('user')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get('test')
  @ApiResponse({ status: 403, description: 'Forbidden.' })
  async test() {
      return this.usersService.test();
  }

  @Post('create')
  @ApiOperation({ title: 'Create a Users entry' })
  @ApiResponse({
    status: 201,
    description: 'The record has been successfully created.',
  })
  @ApiResponse({ status: 403, description: 'Forbidden.' })
  async create(@Body() createUserDto: CreateUserDto) {
    return this.usersService.create(createUserDto);
  }

  @Get('getall')
  @ApiOperation({ title: 'Gets all Users and returns them in an array' })
  @ApiResponse({ status: 403, description: 'Forbidden.' })
  async findAll(): Promise<User[]> {
    return this.usersService.findAll();
  }

  @Get('/:id')
  @ApiOperation({ title: 'Gets a User by id and returns them as a JSON' })
  @ApiResponse({ status: 403, description: 'Forbidden.' })
  async getUser(@Param('id') id: string) {
    return await this.usersService.findById(id);
  }

  @Patch('/:id')
  @ApiOperation({ title: 'Updates a User by id' })
  @ApiResponse({ status: 403, description: 'Forbidden.' })
  async updateUser(@Param('id') id: string, @Body() body: User) {
    return await this.usersService.update(id, body);
  }
}
