import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "../../models/user";
import {Achievement} from "../../models/achievement";

@Injectable({
    providedIn: 'root'
})
export class SystemService {

    user: User;

    constructor(private http: HttpClient) {
    }

    register() {

    }

    notify(user: User, notification: Notification) {

    }

    giveAchievement(achievement: Achievement) {

    }
}
