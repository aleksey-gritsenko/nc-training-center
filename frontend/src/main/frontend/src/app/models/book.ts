import {Entity} from './entity'
import {Author} from  './author'
import {Genre} from "./genre";
export class Book extends Entity {
    header: string;
    overview: string;
    photoId: number;
    fileId: number;
    status: string;
    genre: Genre;
    authors: Array<Author> = [];
}

