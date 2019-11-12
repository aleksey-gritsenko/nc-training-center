import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user: User;

  constructor() { }

  ngOnInit() {
    if (!JSON.parse(localStorage.getItem('currentUser'))) {
      location.assign('/login');
    }
    this.user = JSON.parse(localStorage.getItem('currentUser'))['user'];
  }

  updateUser() {
  }

}
