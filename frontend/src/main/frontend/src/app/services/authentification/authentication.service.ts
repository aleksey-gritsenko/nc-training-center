import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {StorageService} from "../storage/storage.service";
import {User} from "../../models/user";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';

    constructor(private http: HttpClient,
                private storageService: StorageService) {
    }

    public login(username: string, password: string) {
        let form = new FormData();
        form.append('login', username);
        form.append('password', password);

        return this.http.post<any>(`${this.siteUrl}/login`, form)
            .pipe(map(user => {
                user.authdata = window.btoa(username + ':' + password);
                this.storageService.setUser(user);

                return user;
            }));
    }

    logout() {
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
}
