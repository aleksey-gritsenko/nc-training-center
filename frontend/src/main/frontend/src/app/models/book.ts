import {Entity} from './entity'

export class Book extends Entity {
    header: string;
    overview: string;
    photoId: number;
    fileId: number;
    status: string;
}

