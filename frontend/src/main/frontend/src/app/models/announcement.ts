import {Entity} from "./entity";

export class Announcement extends Entity {
    description: string;
    announcementDate: Date ;
    bookID : number;
    priority: string;
    ownerId : number;
    status: string;



}
