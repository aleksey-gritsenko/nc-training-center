import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class RecoverService {

    private siteUrl: string = 'https://nc-group1-2019.herokuapp.com';

    //private siteUrl: string = 'http://localhost:8080';


    constructor(private http: HttpClient, private router: Router) {
    }

    sendRecoverCode(email: string): void {
        const url = `${this.siteUrl}/email`;
        const form = new FormData();
        form.append('email', email);
        alert(email);
        this.http.post(url, form).subscribe();
      //  location.assign('change');
    }
}
