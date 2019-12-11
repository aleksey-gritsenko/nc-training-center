import {Entity} from './entity'
import {Author} from './author'

export class Book extends Entity {
    header: string;
    overview: string;
    status: string;
    genre: string;
    authors: Array<Author> = [];
    photo: number;
    photoURL: string | ArrayBuffer;
    fileURL: string | ArrayBuffer;
}

