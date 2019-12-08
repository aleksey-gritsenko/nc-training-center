import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Achievement} from "../../models/achievement";

@Injectable({
    providedIn: 'root'
})
export class AchievementService {
    // siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';
    siteUrl: string = 'http://localhost:8080';

    constructor(private http: HttpClient) {
    }

    getActions() {
        let url = `${this.siteUrl}/actiontype/all`;
        return this.http.get<{actionTypeId: string, actionName: string, entity: string}[]>(url);
    }

    getGenres() {
        let url = `${this.siteUrl}/book/genresName`;
        return this.http.get<string[]>(url);
    }

    createAchievement(model: Achievement) {
        let url = `${this.siteUrl}/achievement/create`;
        return this.http.post(url, model);
    }

    getUserAchievement(userId:number){
        let url = `${this.siteUrl}/achievement/all`;
        return this.http.post<Achievement[]>(url, userId);
    }
}
