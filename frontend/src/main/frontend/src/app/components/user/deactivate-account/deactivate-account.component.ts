import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-deactivate-account',
    templateUrl: './deactivate-account.component.html',
    styleUrls: ['./deactivate-account.component.css']
})
export class DeactivateAccountComponent implements OnInit {

    status: string;

    constructor(private userService: UserService,
                private route: ActivatedRoute) {
    }

    ngOnInit() {
    }

    deactivateAccount() {
        this.userService.deactivateAccount(this.route.snapshot.paramMap.get('id')).toPromise()
            .then(
                () => {
                    this.status = 'success';
                },
                () => {
                    this.status = 'error';
                }
            );
    }
}
