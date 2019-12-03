import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/user/user.service";

@Component({
    selector: 'app-add-admin',
    templateUrl: './add-admin.component.html',
    styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {
    admin: User = new User();
    repeatPassword: string;

    constructor(private userService: UserService) {
    }

    ngOnInit() {
    }

    createAgent() {
        if (this.admin.userRole == 'admin') this.createAdmin();
    }

    createAdmin() {
        this.userService.createAdmin(this.admin).toPromise().then();
    }
}