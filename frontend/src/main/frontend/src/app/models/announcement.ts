import {Entity} from "./entity";

export class Announcement extends Entity {
    description: string;
    announcementDate: Date ;
    bookID : number;
    priority: string;
    ownerId : number;
    admin_id : number;
    status: string;



}
