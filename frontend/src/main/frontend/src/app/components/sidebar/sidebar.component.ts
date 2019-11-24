import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentification/authentication.service";

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['../landing/landing.component.css']
})
export class SidebarComponent implements OnInit {

    constructor(public serv: AuthenticationService) {
    }

    ngOnInit() {
    }

}
