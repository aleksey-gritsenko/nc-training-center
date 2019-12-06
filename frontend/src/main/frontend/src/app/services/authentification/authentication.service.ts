import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {StorageService} from "../storage/storage.service";
import {User} from "../../models/user";
import {SpringAuthService} from "../security/spring-auth.service";
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    //  private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';
    private siteUrl: string = 'http://localhost:8080';

    constructor(private http: HttpClient,
                private storageService: StorageService,
                private springAuth: SpringAuthService) {
    }

    public login(username: string, password: string): Observable<User> {
        let form = new FormData();
        form.append('login', username);
        form.append('password', password);
        let user: User = new User();

        user.userName = '';
        user.userRole = 'guest';


        this.springAuth.authentificate(username, password).subscribe(data => {
            window.sessionStorage.setItem('token', JSON.stringify(data));
            alert(window.sessionStorage.getItem('token'));
            let tmpUser:User = this.http.post<User>(`${this.siteUrl}/login/` + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, form);
            user.userRole = tmpUser.userRole;
            user.userName = tmpUser.userName;
            user.email = tmpUser.email;
            user.id = tmpUser.id;
        }, error => {
            alert('Error');
        });
        return user;
    }

    logout() {
        window.sessionStorage.removeItem('token');
        this.storageService.setUser(null);
    }

    register(login: string, password: string, email: string) {
        let url = `${this.siteUrl}/registration`;
        let form = new FormData();
        form.append('login', login);
        form.append('password', password);
        form.append('email', email);
        return this.http.post<User>(url, form);
    }

    confirmEmail(email: string, code: string) {
        let url = `${this.siteUrl}/activate`;
        let form = new FormData();
        form.append('email', email);
        form.append('code', code);
        return this.http.post<User>(url, form);
    }

    resendCode(email: string) {
        let url = `${this.siteUrl}/resend`;
        let form = new FormData().append('user', email);
        return this.http.post<boolean>(url, email);
    }
}
