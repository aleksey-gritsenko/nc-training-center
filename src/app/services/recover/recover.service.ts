import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RecoverService {

  constructor(private http: HttpClient, private router: Router) {
  }


  sendRecoverCode(email: string): void {
    const url = 'http://localhost:8080/email';
    const form = new FormData();
    form.append('email', email);
    this.http.post(url, form).subscribe();
    location.assign('change');
  }

}
