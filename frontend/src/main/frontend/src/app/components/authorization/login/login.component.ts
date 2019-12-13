import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentification/authentication.service';
import {StorageService} from "../../../services/storage/storage.service";
import {SpringAuthService} from "../../../services/security/spring-auth.service";
import {Subscription} from "rxjs";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css']
})
export class LoginComponent implements OnInit, OnDestroy {
    loginGroup: FormGroup;

    isError: boolean = false;

    subscription: Subscription;
    loginSubscription: Subscription;

    constructor(
        private http: HttpClient,
        public serv: AuthenticationService,
        private router: Router,
        private storageService: StorageService,
        private springAuth: SpringAuthService) {
    }

    ngOnInit() {
        this.loginGroup = new FormGroup({
            username: new FormControl(''),
            password: new FormControl('')
        });
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
        if (this.loginSubscription) {
            this.loginSubscription.unsubscribe();
        }
    }

    get username() {return this.loginGroup.get('username');}
    get password() {return this.loginGroup.get('password');}

    login(): void {
        this.isError = false;
        let username = this.username.value;
        let password = this.password.value;

        this.subscription = this.springAuth.authentificate(username, password).subscribe(
            data => {
                window.sessionStorage.setItem('token', JSON.stringify(data));

                this.loginSubscription = this.serv.login(username, password).subscribe(
                        user => {
                            if (!user.verified) {
                                this.router.navigateByUrl('/verify');
                            }
                            else {
                                this.storageService.setUser(user);
                                sessionStorage.setItem('user', JSON.stringify(user)); //TODO Should store encrypted user
                                this.router.navigateByUrl('/');
                            }
                        },);
            },
            () => {
                this.isError = true;
                this.loginGroup.patchValue({password: ''});
            }
        );
    }
}
