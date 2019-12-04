import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {AuthenticationService} from "../../services/authentification/authentication.service";
import {StorageService} from "../../services/storage/storage.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit, OnDestroy {
    private user: User;
    searchString: string = '';

    subscription: Subscription;

    constructor(private storageService: StorageService,
                private authenticationService: AuthenticationService
                ) {
        this.subscription = this.storageService.currentUser.subscribe(x => this.user = x);
    }

    ngOnInit() {
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

    logout(): void {
        this.authenticationService.logout();
    }

    clear() {
        this.searchString = '';
    }
}
