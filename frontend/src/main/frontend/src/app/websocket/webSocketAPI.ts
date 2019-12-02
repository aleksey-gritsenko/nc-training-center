import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

export class WebSocketAPI {
    webSocketEndPoint: string = 'http://localhost:8080/ws';
    topic: string = "/notifications/get";
    stompClient: any;

    constructor() {
        this._connect();
    }

    _connect() {
        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
    }

    /*
    _connect() {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect({}, function (frame) {
            _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
                _this.onMessageReceived(sdkEvent);
            });
            //_this.stompClient.reconnect_delay = 2000;
        }, this.errorCallBack);
    };
    */

    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
    }

    // on error, schedule a reconnection attempt
    errorCallBack(error) {
        setTimeout(() => {
            this._connect();
        }, 5000);
    }

    _send(message) {
        this.stompClient.send("/notification/send", {}, JSON.stringify(message));
    }

    // onMessageReceived(message): string {
    //     return message.body;
    // }
}
