import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentification/authentication.service";
import {User} from "../../models/user";

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['../landing/landing.component.css']
})
export class SidebarComponent implements OnInit {
    isLogged: boolean;
    user: User;

    constructor(public serv: AuthenticationService) {
        this.serv.currentUser.subscribe(x => this.user = x);
    }

    ngOnInit() {
        if (this.user) {
            this.isLogged = true;
        }
    }

}
