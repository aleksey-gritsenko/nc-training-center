import {Component, OnDestroy, OnInit} from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {AuthenticationService} from "../../../services/authentification/authentication.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-confirm-email',
    templateUrl: './confirm-email.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css', './confirm-email.component.css']
})
export class ConfirmEmailComponent implements OnInit, OnDestroy {
    code: string = '';
    email: string = '';
    message: string;

    subscription: Subscription;

    constructor(private storageService: StorageService,
                private authenticationService: AuthenticationService,
                private router: Router) {
        if (storageService.getUser()) {
            this.router.navigateByUrl('/');
        }
    }

    ngOnInit() {
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }

    confirm() {
        if (this.email.length != 0 && this.code.length != 0) {
            this.authenticationService.confirmEmail(this.email, this.code).subscribe(
                user => {
                    this.storageService.setUser(user);
                    this.router.navigateByUrl('/')
                },
                error => {
                    this.message = 'error';
                });
        }
    }

    resend() {
        if (this.email.length != 0) {
            this.authenticationService.resendCode(this.email).subscribe(
                result => {
                    this.message = 'send';
                },
                error => {
                    this.message = 'error';
                });
        }
    }
}
