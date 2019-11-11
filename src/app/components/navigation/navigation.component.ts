import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  str:string;
  isLogged: boolean;
  constructor() {}

  ngOnInit() {
      if(JSON.parse(localStorage.getItem('currentUser')) == null) {
          this.isLogged = false;
      } else {
          this.isLogged = true;
          this.str = JSON.parse(localStorage.getItem('currentUser')).userName;
      }
  }
  logout():void{
      localStorage.setItem('currentUser', null);
      localStorage.setItem('isAdmin', null);
    location.assign('/')}
}
