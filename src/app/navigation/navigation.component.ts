import { Component, OnInit } from '@angular/core';

import { User } from '../mdls/user';

import { AuthenticationService } from '../services/authentication.service'

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  user: User;
  str: string;
  isLogged: boolean;
  notLogged: boolean;
  constructor(public serv:AuthenticationService) {
    this.serv.currentUser.subscribe(x => this.user = x);

}

  ngOnInit() {
  }
  logout():void{
    this.serv.logout();
    location.reload()}
}
