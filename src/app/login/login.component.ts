import { Component, OnInit } from '@angular/core';
import { User } from '../mdls/user';
import { LogView} from '../mdls/logview';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model:LogView = {login:'guest',
                password:'guest'};
  constructor(private http: HttpClient) { }

  ngOnInit() {
  }
  login():void{
    let url = 'http://localhost:8080/login';
    let authorization = 'false';
    this.http.post(url,this.model).subscribe(
      res => {authorization = JSON.parse(JSON.stringify(res)).message;
      location.reload()},
      err => {alert(JSON.parse(JSON.stringify(err)).message);}
    );
    if(authorization == 'true'){
      let role = 'user'
      localStorage.setItem('currentUser',JSON.stringify({login:this.model.login, password:this.model.password, role: role}));
    }
  }
}
