import {Component, OnInit} from '@angular/core';
import {User} from "../../../models/user";
import {UserService} from "../../../services/user/user.service";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-add-admin',
    templateUrl: './add-admin.component.html',
    styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {
    admin: User = new User();
    repeatPassword: string;
    user: User;

    editStatus: string;

    constructor(private userService: UserService,
                private storageService: StorageService) {
        this.user = storageService.getUser();
    }

    ngOnInit() {
    }

    createAgent() {
        if (this.admin.userRole == 'admin') this.createAdmin();
    }

    createAdmin() {
        this.userService.createAdmin(this.admin).toPromise()
            .then(
                response => {
                    this.editStatus = 'success';
                },
                error => {
                    this.editStatus = 'error';
                }
            );
    }
}
