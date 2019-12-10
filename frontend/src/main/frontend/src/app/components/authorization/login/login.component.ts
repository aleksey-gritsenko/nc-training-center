import {Component, OnDestroy, OnInit} from '@angular/core';
import {LogView} from '../../../models/logview';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentification/authentication.service';
import {StorageService} from "../../../services/storage/storage.service";
import {SpringAuthService} from "../../../services/security/spring-auth.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css']
})
export class LoginComponent implements OnInit, OnDestroy {
    model: LogView = {
        id: 0,
        login: '',
        password: ''
    };

    isError: boolean = false;
    returnUrl: string;

    subscription: Subscription;
    loginSubscription: Subscription;

    constructor(
        private http: HttpClient, public serv: AuthenticationService,
        private route: ActivatedRoute,
        private router: Router,
        private storageService: StorageService,
        private springAuth: SpringAuthService) {
    }

    ngOnInit() {
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
        if (this.loginSubscription) {
            this.loginSubscription.unsubscribe();
        }
    }

    login(): void {
        this.subscription = this.springAuth.authentificate(this.model.login, this.model.password).subscribe(
            data => {
                window.sessionStorage.setItem('token', JSON.stringify(data));

                if (this.model.login.length == 0 || this.model.password.length == 0) {
                    return;
                }
                this.loginSubscription = this.serv.login(this.model.login, this.model.password).subscribe(
                        user => {
                            if (!user.verified) {
                                this.router.navigateByUrl('/verify');
                            }
                            else {
                                this.storageService.setUser(user);
                                sessionStorage.setItem('user', JSON.stringify(user)); //TODO Should store encrypted user
                                this.router.navigate([this.returnUrl]);
                            }
                        });
            },
            error1 => {
                this.isError = true;
                this.model.password = '';
            }
        );
    }
}
