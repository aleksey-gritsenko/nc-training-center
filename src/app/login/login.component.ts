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
  model:LogView = {login:'',
                password:''};
  constructor(private http: HttpClient) { }

  ngOnInit() {
  }
  login():void{
    let url = 'http://localhost:8080/login';
    var authorization;
    let form = new FormData();
    form.append('login', this.model.login);
    form.append('password', this.model.password);

    this.http.post(url,form).subscribe(

      res => {
        //alert(JSON.parse(JSON.stringify(res)));
      authorization = JSON.parse(JSON.stringify(res));
        if(authorization){
          let role = 'user';
          localStorage.setItem('currentUser',JSON.stringify({login:this.model.login, password:this.model.password, role: role}));

        }
        },
      err => {
      alert(JSON.parse(JSON.stringify(err)).status);
      authorization = false;
      }
    );
    location.reload();
  }
}
