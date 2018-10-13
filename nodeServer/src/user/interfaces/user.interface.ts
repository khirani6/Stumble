import { Document } from 'mongoose';

export interface User extends Document {
  readonly firstName: String;
  readonly lastName: String;
  readonly email: String;
  readonly emergencyContact: String[];
  readonly settings: String[];
}
