import {Injectable} from '@angular/core';
import {User} from "../../models/user";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class StorageService {
    public currentUser: Observable<User>;
    private currentUserSubject: BehaviorSubject<User>;

    constructor() {
        this.currentUserSubject = new BehaviorSubject<User>(null);
        this.currentUser = this.currentUserSubject.asObservable();
    }

    getUser(): User {
        return this.currentUserSubject.getValue();
    }

    setUser(value: User) {
        this.currentUserSubject.next(value);
    }
}
