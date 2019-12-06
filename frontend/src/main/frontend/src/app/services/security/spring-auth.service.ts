import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SpringAuthService {

  constructor(private http: HttpClient ) { }



    authentificate(username: string, password: string) {
        const body = new HttpParams()
            .set('username', username)
            .set('password', password)
            .set('grant_type', 'password');
        const headers = {
            'Authorization': 'Basic ' + btoa('bookNet:bookNet-secret'),
            'Content-type': 'application/x-www-form-urlencoded'
        }
        return this.http.post('http://localhost:8080/' + 'oauth/token', body.toString(), {headers});
    }
}
