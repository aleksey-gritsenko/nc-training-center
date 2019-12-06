import {Component, OnInit} from '@angular/core';
//import {WebSocketAPI} from "../../websocket/webSocketAPI";

@Component({
    selector: 'app-landing',
    templateUrl: './landing.component.html',
    styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
    //private webSocketAPI: WebSocketAPI;

 /*   constructor() {
        this.webSocketAPI = new WebSocketAPI();
    }*/

    ngOnInit() {
    }

 /*   send() {
        this.webSocketAPI._send('Test message');
    }*/

}
