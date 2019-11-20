import {Entity} from "./entity";

export class BookFilter extends Entity {
    author: string[] = [];
    genre: string[] = [];
    header: string = "";
}
