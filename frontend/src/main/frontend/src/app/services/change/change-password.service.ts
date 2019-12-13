import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RecoverService} from "../recover/recover.service";

@Injectable({
    providedIn: 'root'
})
export class ChangePasswordService {

    private siteUrl: string = 'https://nc-group1-2019.herokuapp.com';

    //private siteUrl: string = 'http://localhost:8080';

    constructor(private http: HttpClient, private recService: RecoverService) {
    }

    changePassword(code: string, password: string): void {
        const url = `${this.siteUrl}/change`;
        const form = new FormData();
        form.append('recoverCode', code);
        form.append('newPassword', password);
        this.http.post(url, form).subscribe();
    }

    sendCodeAgain() {
        const url = `${this.siteUrl}/change`;
        const form = new FormData();
        form.append('email', this.recService.userEmail);

        this.recService.sendRecoverCode(this.recService.userEmail);

    }
}
