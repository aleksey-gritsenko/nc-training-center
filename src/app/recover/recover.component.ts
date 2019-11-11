import {Component, OnInit} from '@angular/core';
import {RecView} from '../mdls/recview';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-recover',
  templateUrl: './recover.component.html',
  styleUrls: ['./recover.component.css']
})
export class RecoverComponent implements OnInit {
  model: RecView = {
    login: '',
    email: ''
  };

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }


  recover(): void {
    let url = 'http://localhost:8080/userrrecover';
    let form = new FormData();
    form.append('login', this.model.login);
    form.append('email', this.model.email);
    this.http.post(url, form).subscribe();
    alert(this.model.email);
  }
}
