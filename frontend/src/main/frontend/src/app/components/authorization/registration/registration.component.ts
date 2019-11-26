import {Component, OnInit} from '@angular/core';
import {RegView} from '../../../models/regview';
import {HttpClient} from '@angular/common/http';
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {AuthenticationService} from "../../../services/authentification/authentication.service";

@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
    model: RegView = {
        id: 0,
        login: '',
        password: '',
        email: ''
    };

    constructor(private http: HttpClient,
                private router: Router,
                private storage: StorageService,
                private authenticationService: AuthenticationService) {
    }

    confirmPassword: string = '';

    ngOnInit() {
    }

    register(): void {
        this.authenticationService.register(this.model.login, this.model.password, this.model.email).subscribe(
            res => {
                if (res != null) {
                    this.storage.setUser(res);
                    this.router.navigateByUrl('/')
                }
            },
            err => {
                console.log(err);
            }
        );
    }
}
