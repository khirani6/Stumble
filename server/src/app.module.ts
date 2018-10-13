import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { UsersModule } from './user/users.module';

@Module({
  imports: [
      MongooseModule.forRoot('mongodb://localhost:27017/stumble'),
      UsersModule
    ]
})
export class AppModule {}
