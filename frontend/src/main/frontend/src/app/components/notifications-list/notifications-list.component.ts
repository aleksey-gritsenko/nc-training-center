/*
import {Component, OnDestroy, OnInit} from '@angular/core';
import {WebSocketAPI} from "../../websocket/webSocketAPI";

@Component({
    selector: 'app-notifications-list',
    templateUrl: './notifications-list.component.html',
    styleUrls: ['./notifications-list.component.css']
})
export class NotificationsListComponent implements OnInit, OnDestroy {
    messages: string[] = [];

    private webSocketAPI: WebSocketAPI;

    constructor() {
        this.webSocketAPI = new WebSocketAPI();
    }

    ngOnInit() {
        this.connect();
    }

    ngOnDestroy(): void {
        this.webSocketAPI._disconnect();
    }

    connect() {
        const _this = this;
        _this.webSocketAPI.stompClient.connect({}, function (frame) {
            _this.webSocketAPI.stompClient.subscribe(_this.webSocketAPI.topic, function (sdkEvent) {
                _this.onMessageReceived(sdkEvent);
            });
        }, this.webSocketAPI.errorCallBack);
    }

    send(message) {
        this.webSocketAPI._send(message);
    }

    onMessageReceived(message) {
        this.messages.unshift(JSON.parse(message.body)['message']);
    }
}
*/
