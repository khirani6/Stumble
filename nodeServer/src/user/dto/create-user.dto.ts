import { IsString } from 'class-validator';
import { ApiModelProperty } from '@nestjs/swagger';

export class CreateUserDto {
  @ApiModelProperty()
  @IsString()
  readonly firstName: String;

  @ApiModelProperty()
  @IsString()
  readonly lastName: String;

  @ApiModelProperty()
  @IsString()
  readonly email: String;

  @ApiModelProperty()
  readonly emergencyContacts: String[];

  @ApiModelProperty()
  @IsString()
  readonly settings: String[];
}
