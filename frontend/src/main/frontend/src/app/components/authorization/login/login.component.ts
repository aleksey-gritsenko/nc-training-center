import {Component, OnInit} from '@angular/core';
import {LogView} from '../../../models/logview';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

import {AuthenticationService} from '../../../services/authentification/authentication.service';
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css']
})
export class LoginComponent implements OnInit {
    model: LogView = {
        id: 0,
        login: '',
        password: ''
    };

    isError: boolean = false;
    returnUrl: string;
    errorMessage = '';

    constructor(
        private http: HttpClient, public serv: AuthenticationService,
        private route: ActivatedRoute,
        private router: Router,
        private storageService: StorageService) {
    }

    ngOnInit() {
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    login(): void {
        // this.serv.login(this.model.login, this.model.password)
        //     .pipe(first())
        //     .subscribe(
        //         data => {
        //             this.router.navigate([this.returnUrl]);
        //         },
        //         error => {
        //             this.isError = true;
        //             this.model.password = '';
        //             this.model.login = '';
        //             //  this.error = error;
        //             //  this.loading = false;
        //         }
        //     );
        if (this.model.login.length == 0 || this.model.password.length == 0) return;
        this.serv.login(this.model.login, this.model.password)
            .subscribe(
                user => {
                 if (!user.verified) this.router.navigateByUrl('/verify');
                 else {
                     this.storageService.setUser(user);
                     this.router.navigate([this.returnUrl]);
                 }
        },
            error => {
                if (error.status == 404) this.isError = true;
                this.model.password = '';
            });
    }
}
