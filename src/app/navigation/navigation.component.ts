import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  str:string;
  constructor() {
   if(localStorage.getItem('currentUser')==null){
      localStorage.setItem('currentUser',JSON.stringify({login:'', password:'', role: 'guest'}));
   }
   this.str = JSON.parse(localStorage.getItem('currentUser')).login;

   }

  ngOnInit() {
  }

}
