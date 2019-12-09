import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {userSearch} from "../../models/userSearch";
import {UserService} from "../../services/user/user.service";
import {User} from "../../models/user";
import {StorageService} from "../../services/storage/storage.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy {
    search: string = '';
    userModel: userSearch[] = [];
    currUser: User;

    private routSubscription: Subscription;
    private userSubscription: Subscription;

    constructor(private route: ActivatedRoute,
                private userService: UserService,
                private storageService: StorageService) {}

    ngOnInit() {
        this.userSubscription = this.storageService.currentUser.subscribe(
            user => {
            this.currUser = user;
        });
        this.routSubscription = this.route.queryParams.subscribe(
            param => {
                if (param.search.length > 0) {
                    this.search = param.search;
                    this.userService.searchByUsername(this.search).toPromise().then(
                        res => {
                            this.userModel = res;
                        },
                        () => {
                            this.userModel = null;
                        });
                } else this.search = null; //TODO Should return all users
            })
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe();
        this.routSubscription.unsubscribe();
    }
}
