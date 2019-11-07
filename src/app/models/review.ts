import { Book } from './book';
import { User } from './user';

export class Review{
    book: Book;
    user: User;
    text: string; // or Text
}