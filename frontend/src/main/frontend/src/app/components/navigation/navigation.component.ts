import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {AuthenticationService} from "../../services/authentification/authentication.service";
import {StorageService} from "../../services/storage/storage.service";

@Component({
    selector: 'app-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
    private user: User;

    constructor(private storageService: StorageService,
                private authenticationService: AuthenticationService
                ) {
        this.storageService.currentUser.subscribe(x => this.user = x);
    }

    ngOnInit() {
    }

    logout(): void {
        this.authenticationService.logout();

    }
}
