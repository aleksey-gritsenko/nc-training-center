import { Component, OnInit } from '@angular/core';
import { User } from '../mdls/user';
import { RegView} from '../mdls/regview';
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

  ngOnInit() {
  }
  register():void{
    let url = 'http://localhost:8080/registration';
    let form = new FormData();
    form.append('login', this.model.login);
    form.append('password', this.model.password);
    form.append('email',this.model.email)
    this.http.post(url,form).subscribe(
      res => {location.reload()},
      err => {alert(JSON.parse(JSON.stringify(err)).message);}
    );
  }
}
