import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ChangePasswordService {

    constructor(private http: HttpClient) {
    }

    changePassword(code: string, password: string): void {
        const url = 'http://localhost:8080/change';
        const form = new FormData();
        form.append('recoverCode', code);
        form.append('newPassword', password);
        this.http.post(url, form).subscribe();

    }
}
