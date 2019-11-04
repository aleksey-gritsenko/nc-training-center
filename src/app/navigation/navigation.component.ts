import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  str:string;
  constructor() {
   this.str = JSON.parse(localStorage.getItem('currentUser')).login;
   }

  ngOnInit() {
  }

}
