import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {AuthenticationService} from "../../services/authentification/authentication.service";
import {StorageService} from "../../services/storage/storage.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
    private user: User;

    constructor(private storageService: StorageService,
                private authenticationService: AuthenticationService,
                private router: Router) {
        this.storageService.currentUser.subscribe(x => this.user = x);
    }

    ngOnInit() {
    }

    logout(): void {
        // this.router.navigateByUrl('/');
        this.authenticationService.logout();
    }
}
