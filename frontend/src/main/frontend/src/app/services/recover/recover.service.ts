import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class RecoverService {

    public userEmail: string = '';

  //  private siteUrl: string = 'https://nc-group1-2019.herokuapp.com';

    private siteUrl: string = 'http://localhost:8080';


    constructor(private http: HttpClient, private router: Router) {
    }

    sendRecoverCode(email: string): void {
        const url = `${this.siteUrl}/email`;
        const form = new FormData();
        this.userEmail = email;
        form.append('email', this.userEmail);
        alert(this.userEmail);
        this.http.post(url, form).subscribe();
        //  location.assign('change');
    }
}
