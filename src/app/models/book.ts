import {Entity} from './entity'

export class Book extends Entity {
    header: string;
    authors: string[];
    overview: string;
    photoId: string;
    fileId: number;
    status: string;
    genres: string[];
}

