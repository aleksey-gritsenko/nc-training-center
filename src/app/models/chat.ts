import {Message} from './message';
import {Entity} from "./entity";

export class Chat extends Entity {
    title: string;
    messages: Message[];
}
