import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ChangePasswordService {

    private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';
    //private siteUrl: string = 'http://localhost:8080';

    constructor(private http: HttpClient) {
    }

    changePassword(code: string, password: string): void {
        const url = `${this.siteUrl}/change`;
        const form = new FormData();
        form.append('recoverCode', code);
        form.append('newPassword', password);
        this.http.post(url, form).subscribe();

    }
}
