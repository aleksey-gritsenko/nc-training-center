import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class NotificationsService {
    siteUrl: string = 'https://nc-group1-2019.herokuapp.com';
    //siteUrl: string = 'http://localhost:8080';

    constructor() {

    }
}
