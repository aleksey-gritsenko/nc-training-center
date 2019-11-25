import {Entity} from "./entity";

export class User extends Entity {
    userName: string;
    userPassword: string;
    email: string;
    userRole: string;
    recoverCode: string;
}
