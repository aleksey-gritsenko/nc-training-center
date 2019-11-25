import {Entity} from "./entity";

export class Review extends Entity {
    userId: number;
    bookId: number;
    text: string;
    reviewDate: Date;
    grade: number;
    adminId: number;
    status: boolean;
}
