import {Book} from './book';
import {User} from './user';
import {Entity} from "./entity";

export class Review extends Entity {
    user: User;
    book: Book;
    text: string;
    reviewDate: Date;
    grade: number;
    adminId: number;
    status: boolean;
}
