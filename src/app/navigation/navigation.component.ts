import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  str:string;
  isLogged:boolean;
  notLogged:boolean;
  constructor() {
   if(localStorage.getItem('currentUser')==null){
      localStorage.setItem('currentUser',JSON.stringify({login:'', password:'', role: 'guest'}));
   }
  else{
    if(JSON.parse(localStorage.getItem('currentUser')).login == ''){
      this.isLogged = false;
      this.notLogged = true;
    }
    else{
      this.isLogged = true;
      this.notLogged = false;

    }
  }
   this.str = JSON.parse(localStorage.getItem('currentUser')).login;

   }

  ngOnInit() {
  }

}
