import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {AuthenticationService} from "../../services/authentification/authentication.service";

@Component({
    selector: 'app-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
    user: User;

    constructor(public serv: AuthenticationService) {
        this.serv.currentUser.subscribe(x => this.user = x);
    }

    ngOnInit() {
    }

    logout(): void {
        this.serv.logout();
        location.reload();
    }
}
