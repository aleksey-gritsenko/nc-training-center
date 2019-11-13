import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/user/user.service";
import {LogView} from "../../models/logview";

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
    user: User;
    currentUser: string;

    constructor(private userService: UserService) {
    }

    ngOnInit() {
        this.user = JSON.parse(localStorage.getItem('currentUser'));
        if (!this.user) {
            location.assign('/login');
        }

        this.currentUser = this.user.userName;
    }

    update(): void {
        this.userService.updateProfile(this.currentUser, this.user)
            .subscribe(
                user => {
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.user = user;
                    this.currentUser = this.user.userName;
                }
            );
    }

}
