import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {StorageService} from "../storage/storage.service";
import {User} from "../../models/user";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private http: HttpClient,
                private storageService: StorageService) {
    }

    public login(username: string, password: string) {
        let form = new FormData();
        form.append('login', username);
        form.append('password', password);

        return this.http.post<any>('http://localhost:8080/login', form)
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
        let url = 'http://localhost:8080/registration';
        let form = new FormData();
        form.append('login', login);
        form.append('password', password);
        form.append('email', email);

        return this.http.post<User>(url, form);
    }
}
