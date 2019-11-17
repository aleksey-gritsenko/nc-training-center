import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Changeview} from '../../models/changeview';
import {ChangePasswordService} from '../../services/change/change-password.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  model: Changeview = {
    code: '',
    password: '',
    confirmPassword: ''
  };


  constructor(private service: ChangePasswordService) {
  }

  ngOnInit() {
  }

  change(): void {
    this.service.changePassword(this.model.code, this.model.password);
  }

}
