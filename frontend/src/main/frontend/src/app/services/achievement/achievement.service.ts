import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Achievement} from "../../models/achievement";
import {User} from "../../models/user";

@Injectable({
    providedIn: 'root'
})
export class AchievementService {
    siteUrl: string = 'https://nc-group1-2019.herokuapp.com';

    // siteUrl: string = 'http://localhost:8080';

    constructor(private http: HttpClient) {
    }

    getActions() {
        let url = `${this.siteUrl}/actiontype/all`;
        return this.http.get<any[]>(url);
    }

    getGenres() {
        let url = `${this.siteUrl}/book/genres`;
        return this.http.get<any[]>(url);
    }

    createAchievement(model: Achievement) {
        let url = `${this.siteUrl}/achievement/create`;
        return this.http.post(url, model);
    }

    getUserAchievement(user: User) {

        let url = `${this.siteUrl}/achievement/all`;
        return this.http.post<Achievement[]>(url, user);
    }
}
