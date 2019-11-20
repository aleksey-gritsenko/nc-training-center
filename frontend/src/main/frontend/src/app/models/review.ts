import {Book} from './book';
import {User} from './user';
import {Entity} from "./entity";

export class Review extends Entity {
    book: Book;
    user: User;
    text: string;
    reviewDate: Date;
    grade: number;
    adminId: number;
}
