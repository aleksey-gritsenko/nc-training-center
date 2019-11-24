import {Entity} from "./entity";

export class User extends Entity {
    id: number;
    userName: string;
    userPassword: string;
    email: string;
    userRole: string;
}
