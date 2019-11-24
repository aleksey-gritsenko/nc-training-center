import {Component, OnInit} from '@angular/core';

export interface IMessage {
    id: number;
    text: string;
}

@Component({
    selector: 'app-notifications-list',
    templateUrl: './notifications-list.component.html',
    styleUrls: ['./notifications-list.component.css']
})
export class NotificationsListComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
    }

}
