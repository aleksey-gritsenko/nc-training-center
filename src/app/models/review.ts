import { Book } from './book';
import { User } from './user';
import {Id} from "./id";

export class Review extends Id {
    book: Book;
    user: User;
    text: string;
    reviewDate: Date;
    grade: number;
    adminId: number;
}
