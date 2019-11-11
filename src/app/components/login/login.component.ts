import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { LogView} from '../../models/logview';
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
    let authorization;
    let form = new FormData();
    form.append('login', this.model.login);
    form.append('password', this.model.password);

      this.http.post(url,form).subscribe (
          res => {
              if (res != null) {
                  localStorage.setItem('isAdmin', JSON.stringify(res['isAdmin']));
                  localStorage.setItem('currentUser', JSON.stringify(res['user']))
                  location.assign('/');
              }
          }
      )
   }
}
