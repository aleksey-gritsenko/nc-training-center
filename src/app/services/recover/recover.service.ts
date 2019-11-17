import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RecoverService {

  constructor(private http: HttpClient) {
  }


  sendRecoverCode(email: string): void {
    const url = 'http://localhost:8080/email';
    const form = new FormData();
    form.append('email', email);
    this.http.post(url, form).subscribe();
    location.assign('change');
  }

}
