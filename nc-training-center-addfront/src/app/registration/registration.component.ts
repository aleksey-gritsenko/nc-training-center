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
    this.http.post(url,this.model).subscribe(
      res => {location.reload()},
      err => {alert(JSON.parse(JSON.stringify(err)).message);}
    );
  }
}
