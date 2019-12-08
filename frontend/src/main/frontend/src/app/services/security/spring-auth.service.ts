import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SpringAuthService {

    private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';
    // if you want to test your code on localhost - change siteUrl to localhost where this is needed
    private localhost: string = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

    authentificate(username: string, password: string) {
        const body = new HttpParams()
            .set('username', username)
            .set('password', password)
            .set('grant_type', 'password');
        const headers = {
            'Authorization': 'Basic ' + btoa('bookNet:bookNet-secret'),
            'Content-type': 'application/x-www-form-urlencoded'
        };
        return this.http.post(this.localhost + 'oauth/token', body.toString(), {headers});
    }
}
