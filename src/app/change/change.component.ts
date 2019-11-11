import {Component, OnInit} from '@angular/core';
import {Changeview} from '../mdls/changeview';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-change',
  templateUrl: './change.component.html',
  styleUrls: ['./change.component.css']
})
export class ChangeComponent implements OnInit {
  model: Changeview = {
    login: '',
    code: '',
    password: ''
  };

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }

  change(): void {
    let url = 'http://localhost:8080/change';
    let form = new FormData();
    form.append('login', this.model.login);
    form.append('recoverCode', this.model.code);
    form.append('newPassword', this.model.password);
    this.http.post(url, form).subscribe();
    // alert(this.model.recoverCod);
  }

}
