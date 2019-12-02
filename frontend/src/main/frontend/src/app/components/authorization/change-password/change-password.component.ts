import {Component, OnInit} from '@angular/core';
import {Changeview} from '../../../models/changeview';
import {ChangePasswordService} from '../../../services/change/change-password.service';
import {Router} from "@angular/router";

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css']
})
export class ChangePasswordComponent implements OnInit {
    model: Changeview = {
        id: 0,
        code: '',
        password: '',
        confirmPassword: ''
    };


    constructor(private service: ChangePasswordService,
                private router: Router) {
    }

    ngOnInit() {
    }

    change(): void {
        this.service.changePassword(this.model.code, this.model.password);
        this.router.navigateByUrl('/login');
    }

}
