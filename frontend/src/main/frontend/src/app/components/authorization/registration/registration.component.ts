import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {AuthenticationService} from "../../../services/authentification/authentication.service";
import {Subscription} from "rxjs";
import {SpringAuthService} from "../../../services/security/spring-auth.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user/user.service";

@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css', './registration.component.css']
})
export class RegistrationComponent implements OnInit, OnDestroy {
    regGroup: FormGroup;

    regSubscription: Subscription;
    securitySubscription: Subscription;

    isError: boolean;

    constructor(private http: HttpClient,
                private router: Router,
                private storage: StorageService,
                private authenticationService: AuthenticationService,
                private springAuth: SpringAuthService,
                private userService: UserService) {
        if (storage.getUser()) {
            this.router.navigateByUrl('/');
        }
    }

    ngOnInit() {
        this.regGroup = new FormGroup({
            username: new FormControl('', [Validators.minLength(4), Validators.pattern(this.userService.inputRegExp)]),
            email: new FormControl('', [Validators.pattern(this.userService.emailRegExp)]),
            password: new FormControl('', Validators.pattern(this.userService.passwordRegExp)),
            confirmPassword: new FormControl('')
        });
    }

    get username() {return this.regGroup.get('username');}
    get password() {return this.regGroup.get('password');}
    get email() {return this.regGroup.get('email');}
    get confirmPassword() {return this.regGroup.get('confirmPassword');}

    register(): void {
        this.isError = false;
        let username = this.username.value;
        let password = this.password.value;

        this.regSubscription = this.authenticationService.register(username, password, this.email.value).subscribe(
            res => {
                this.securitySubscription = this.springAuth.authentificate(username, password).subscribe(
                    data => {
                        window.sessionStorage.setItem('token', JSON.stringify(data));
                        this.router.navigateByUrl('/verify');
                    });
            },
            err => {
                this.isError = true;
                this.regGroup.patchValue({password: '', confirmPassword: ''});
            }
        );
    }

    ngOnDestroy(): void {
        if (this.regSubscription) this.regSubscription.unsubscribe();
        if (this.securitySubscription) this.securitySubscription.unsubscribe();
    }
}
