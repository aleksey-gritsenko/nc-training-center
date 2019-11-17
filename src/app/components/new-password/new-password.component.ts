import {Component, OnInit} from '@angular/core';
import {NewPassword} from "../../models/newpassword";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit {
  model: NewPassword = {
    recoverCode: '',
    newPass: ''
  };

  constructor(private http: HttpClient) {

  }

  ngOnInit() {

  }

  change(): void {
    let url = 'http://localhost:8080/change';
    let form = new FormData();
    form.append('recoverCode', this.model.recoverCode);
    form.append('newPassword', this.model.newPass);
    this.http.post(url, form).subscribe();
    alert(this.model.recoverCode);
  }


}
