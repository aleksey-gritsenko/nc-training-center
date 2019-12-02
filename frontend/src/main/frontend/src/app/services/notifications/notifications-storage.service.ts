import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {NotificationModel} from "../../models/notification";
import {NotificationsService} from "./notifications.service";

@Injectable({
    providedIn: 'root'
})
export class NotificationsStorageService {
    public notificationsList: Observable<NotificationModel>;
    private notificationsListSubject: BehaviorSubject<NotificationModel>;

    private notifications: NotificationModel[];

    constructor(private notificationsService: NotificationsService) {
        this.notificationsListSubject = new BehaviorSubject<NotificationModel>(null);
        this.notificationsList = this.notificationsListSubject.asObservable();


    }

    loadNotificationsFromServer() {

    }

    setNotification(message: NotificationModel) {
        this.notificationsListSubject.next(message);
    }

    getNotifications() {
        return this.notifications;
    }

    disconnect() {

    }
}
