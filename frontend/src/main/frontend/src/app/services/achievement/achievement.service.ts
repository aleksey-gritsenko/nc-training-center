import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Achievement} from "../../models/achievement";
import {User} from "../../models/user";
import {Action} from "../../models/action";

@Injectable({
    providedIn: 'root'
})
export class AchievementService {
    private siteUrl: string = 'https://nc-group1-2019.herokuapp.com';
    //siteUrl: string = 'http://localhost:8080';

    constructor(private http: HttpClient) {
    }

    getActions(){
        let url = `${this.siteUrl}/actiontype/all`;
        return this.http.get<Action[]>(url);
    }

    createAchievement(model: Achievement) {
        let url = `${this.siteUrl}/achievement/create`;
        return this.http.post(url, model);
    }

    getUserAchievement(user: User) {
        let url = `${this.siteUrl}/achievement/all-for-user`;
        return this.http.post<Achievement[]>(url, user);
    }
}
