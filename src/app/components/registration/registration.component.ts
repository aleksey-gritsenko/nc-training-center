import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { RegView} from '../../models/regview';
import { HttpClient } from '@angular/common/http';

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
  constructor(private http: HttpClient) { }

  confirmPassword: string = '';

  ngOnInit() {
  }
  register():void{
    let url = 'http://localhost:8080/registration';
    let form = new FormData();
    form.append('login', this.model.login);
    form.append('password', this.model.password);

    form.append('email',this.model.email);
    form.append('email',this.model.email)
    this.http.post(url,form).subscribe(
      res => {
        if (res != null) {
          // localStorage.setItem('isAdmin', 'false');
          localStorage.setItem('currentUser', JSON.stringify(res));
          location.assign('/');
        }
      },
      err => {alert(JSON.parse(JSON.stringify(err)).message);}
    );
  }
}
