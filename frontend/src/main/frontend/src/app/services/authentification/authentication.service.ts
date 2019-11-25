import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {StorageService} from "../storage/storage.service";

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
                // localStorage.setItem('currentUser', JSON.stringify(user));
                this.storageService.setUser(user);

                return user;
            }));
    }

    logout() {
        // localStorage.removeItem('currentUser');
        this.storageService.setUser(null);
    }
}
