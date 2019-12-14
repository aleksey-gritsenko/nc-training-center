import {Component, OnInit} from '@angular/core';
import {Changeview} from '../../../models/changeview';
import {ChangePasswordService} from '../../../services/change/change-password.service';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user/user.service";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css']
})
export class ChangePasswordComponent implements OnInit {
    changeGroup: FormGroup;
    model: Changeview = {
        id: 0,
        code: '',
        password: '',
        confirmPassword: ''
    };

    constructor(private service: ChangePasswordService,
                private router: Router,
                private userService: UserService,
                private storageService: StorageService) {
        if (storageService.getUser()) {
            this.router.navigateByUrl('/');
        }
    }

    get code() {
        return this.changeGroup.get('code');
    }

    get password() {
        return this.changeGroup.get('password');
    }

    get confirmPassword() {
        return this.changeGroup.get('confirmPassword');
    }

    ngOnInit() {
        this.changeGroup = new FormGroup({
                password: new FormControl('', Validators.pattern(this.userService.passwordRegExp)),
                code: new FormControl('', Validators.minLength(4)),
                confirmPassword: new FormControl('', Validators.pattern(this.userService.passwordRegExp))
            },
        )
    }

    change(): void {
        alert(this.model.code);
        this.service.changePassword(this.model.code, this.model.password);
        this.router.navigateByUrl('/login');
    }

    sendAgain(): void {
        this.service.sendCodeAgain();
    }

}
