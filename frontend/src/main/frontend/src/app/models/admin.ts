import {Entity} from "./entity";

export class Admin extends Entity {
    adminName: string;
    adminPassword: string;
    email: string;
    adminRole: string;
}
