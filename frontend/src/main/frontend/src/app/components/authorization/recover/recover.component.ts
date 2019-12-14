import {Component, OnInit} from '@angular/core';
import {RecView} from '../../../models/recview';
import {RecoverService} from '../../../services/recover/recover.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user/user.service";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-recover',
    templateUrl: './recover.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css']
})
export class RecoverComponent implements OnInit {
    recoverGroup: FormGroup;
    model: RecView = {
        id: 0,
        login: '',
        email: ''
    };

    constructor(private recoverService: RecoverService,
                private userService: UserService,
                private router: Router,
                private storageService: StorageService) {
        if (storageService.getUser()) {
            this.router.navigateByUrl('/');
        }
    }

    ngOnInit() {
        this.recoverGroup = new FormGroup({email: new FormControl('', [Validators.pattern(this.userService.emailRegExp)])})

    }

    get email() {
        return this.recoverGroup.get('email');
    }


    recover(): void {
        this.recoverService.sendRecoverCode(this.model.email);
        this.router.navigateByUrl('/change')
    }
}
