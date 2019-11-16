import {Component, OnInit} from '@angular/core';
import {RecView} from "../mdls/recview";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-recover-password',
  templateUrl: './recover-password.component.html',
  styleUrls: ['./recover-password.component.css']
})
export class RecoverPasswordComponent implements OnInit {
  model: RecView = {
    email: ''
  };


  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }

  recover(): void {
    let url = 'http://localhost:8080/recover';
    let form = new FormData();
    form.append('email', this.model.email);
    this.http.post(url, form).subscribe();
    alert(this.model.email);
  }

}
