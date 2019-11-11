import { Component, OnInit } from '@angular/core';
import { User } from '../mdls/user';
import { RegView} from '../mdls/regview';
import { HttpClient } from '@angular/common/http';

import { AuthenticationService } from '../services/authentication.service'

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  model:RegView = {
    login:'',
    password:'',
    email:''
  };
  constructor(private http: HttpClient, private serv: AuthenticationService) { }

  confirmPassword: string = '';

  ngOnInit() {
  }
  register():void{
    let url = 'http://localhost:8080/user/update';
    let form = new FormData();
    let log;
    this.serv.currentUser.subscribe(x => log = x);
    form.append('login', log);
    form.append('newLogin', this.model.login);
    form.append('newPassword', this.model.password);
    form.append('newEmail',this.model.email);
    this.http.post(url,form).subscribe(
      res => {
      alert(JSON.stringify(res));
      location.reload();},
      err => {alert(JSON.parse(JSON.stringify(err)).message);}
    );
  }
}
