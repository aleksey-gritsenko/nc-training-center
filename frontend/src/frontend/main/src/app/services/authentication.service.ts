import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../mdls/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  constructor() {
      var user:User = JSON.parse(localStorage.getItem('currentUser'));
      this.currentUserSubject = new BehaviorSubject<User>(user);
      this.currentUser = this.currentUserSubject.asObservable();
  }
}
