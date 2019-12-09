import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../services/common/common.service";
import {Book} from "../../models/book";
//import {WebSocketAPI} from "../../websocket/webSocketAPI";

@Component({
    selector: 'app-landing',
    templateUrl: './landing.component.html',
    styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
    //private webSocketAPI: WebSocketAPI;
    books: Book[] = [];

    constructor(private commonService: CommonService) {
        // this.webSocketAPI = new WebSocketAPI();
        commonService.getBooks().toPromise().then(
            books => {
                this.books = books;
            }
        )
    }

    ngOnInit() {
    }

 /*   send() {
        this.webSocketAPI._send('Test message');
    }*/

}
