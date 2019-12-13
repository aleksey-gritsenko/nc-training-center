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
    routSubscription: Subscription;
    searchSubscription: Subscription;

    currentUser: User; //The user in the system
    user: User = new User(); //The user page we look at

    constructor(private storageService: StorageService,
                private userService: UserService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    ngOnInit() {
        this.userSubscription = this.storageService.currentUser.subscribe(user => {
            if (!user) {
                this.router.navigateByUrl('/login');
            }
            else {
                this.currentUser = this.user = user;

                this.routSubscription = this.route.params.subscribe(param => {
                    this.isOpen = 'View';
                    if (this.currentUser.id != param.id) {
                        this.getUserInfo(param.id);
                    }
                    else {
                        this.user = this.currentUser;
                    }
                });
            }
        });


    }

    open(tab: string) {
        this.isOpen = tab;
    }

    getUserInfo(id: string) {
        this.user = new User();
        this.searchSubscription = this.userService.searchUser(id).subscribe(
            user => {
                if (this.currentUser.userRole == 'user' && user.userRole != 'user') {
                    this.router.navigateByUrl('/error');
                }
                this.user = user;
            },
            error => {
                this.router.navigateByUrl('/error');
            });
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe();

        if (this.routSubscription)  {
            this.routSubscription.unsubscribe();
        }

        if (this.searchSubscription)  {
            this.searchSubscription.unsubscribe();
        }
    }
}
