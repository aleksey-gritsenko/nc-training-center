import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['../landing/landing.component.css']
})
export class SidebarComponent implements OnInit {
  isLogged: boolean;
  constructor() { }

  ngOnInit() {
    if (JSON.parse(localStorage.getItem('currentUser')) != null) {
      this.isLogged = true;
    }
  }

}
