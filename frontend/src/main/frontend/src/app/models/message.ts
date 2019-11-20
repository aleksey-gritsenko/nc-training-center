import {User} from './user';
import {Entity} from "./entity";

export class Message extends Entity {
    user: User;
    text: string;
}
