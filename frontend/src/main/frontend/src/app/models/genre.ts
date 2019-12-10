import {Entity} from "./entity";
import {Book} from "./book";

export class Genre extends Entity {
    name: string;
    books: Book[];
}
