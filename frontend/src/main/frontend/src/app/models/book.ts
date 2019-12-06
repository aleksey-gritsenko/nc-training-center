import {Entity} from './entity'
import {Author} from  './author'
import {Genre} from "./genre";
export class Book extends Entity {
    header: string;
    overview: string;
    photo: string|ArrayBuffer;
    file: string|ArrayBuffer;
    status: string;
    genre: string;
    authors: Array<Author> = [];
}

