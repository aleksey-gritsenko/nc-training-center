import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "../../../models/user";
import {StorageService} from "../../../services/storage/storage.service";
import {Subscription} from "rxjs";
import {UserService} from "../../../services/user/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit, OnDestroy {
    isOpen: string; // Which tab is open

    userSubscription: Subscription;
    // routSubscription: Subscription;

    currentUser: User; //The user in the system
    isCurrUserAnAdmin: boolean;
    isThisCurrUserProfile: boolean;
    isAllowedToChange: boolean;
    isAllowedToAdd: boolean;
    isAllowedToDeactivate: boolean;

    user: User = new User(); //The user page we look at

    constructor(private storageService: StorageService,
                private userService: UserService,
                private route: ActivatedRoute,
                private router: Router) {
        this.isOpen = "View";

        this.userSubscription = this.storageService.currentUser.subscribe(user => {
            if (!user) this.router.navigateByUrl('/login');
            else {
                this.currentUser = this.user = user;
            }
        });
    }

    ngOnInit() {
        this.isCurrUserAnAdmin = this.currentUser.userRole != 'user';

        this.route.params.subscribe(param => {
            this.isOpen = 'View';
            if (this.currentUser.id != param.id) this.getUserInfo(param.id);
            else {
                this.user = this.currentUser;
                this.isThisCurrUserProfile = true;
                this.isAllowedToChange = this.currentUser.userRole == 'user' || this.currentUser.userRole == 'super';
                this.isAllowedToAdd = this.currentUser.userRole == 'super' || this.currentUser.userRole == 'admin';
                this.isAllowedToDeactivate = false;
            }
        })
    }

    open(tab: string) {
        this.isOpen = tab;
    }

     getUserInfo(id: string) {
        this.user = new User();
        this.userService.searchUser(id).toPromise().then(
            user => {
            if (!this.isCurrUserAnAdmin && user.userRole != 'user') this.router.navigateByUrl('/error');
            this.user = user;
            this.isAllowedToDeactivate = this.isAllowedToChange = this.currentUser.userRole == 'super' && user.userRole != 'user' || (this.currentUser.userRole == 'admin' && user.userRole == 'moderator');
        },
            error => {
                this.router.navigateByUrl('/error');
            });
        this.isThisCurrUserProfile = false;
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe();
        // this.routSubscription.unsubscribe();
    }
}
