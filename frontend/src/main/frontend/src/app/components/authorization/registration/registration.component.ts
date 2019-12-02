import {Component, OnDestroy, OnInit} from '@angular/core';
import {RegView} from '../../../models/regview';
import {HttpClient} from '@angular/common/http';
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {AuthenticationService} from "../../../services/authentification/authentication.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css']
})
export class RegistrationComponent implements OnInit, OnDestroy {
    model: RegView = {
        id: 0,
        login: '',
        password: '',
        email: ''
    };

    subscription: Subscription;
    isError: boolean;

    constructor(private http: HttpClient,
                private router: Router,
                private storage: StorageService,
                private authenticationService: AuthenticationService) {
    }

    confirmPassword: string = '';

    ngOnInit() {
    }

    register(): void {
        this.subscription = this.authenticationService.register(this.model.login, this.model.password, this.model.email).subscribe(
            res => {
                this.router.navigateByUrl('/activate');
            },
            err => {
                this.isError = true;
            }
        );
    }

    ngOnDestroy(): void {
        if (this.subscription) this.subscription.unsubscribe();
    }
}
