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

    routSubscription: Subscription;
    searchSubscription: Subscription;
    getAllSubscription: Subscription;

    constructor(private route: ActivatedRoute,
                private userService: UserService) {
    }

    ngOnInit() {
        this.routSubscription = this.route.queryParams.subscribe(
            param => {
                this.userModel = [];
                if (param.search.length > 0) {
                    this.search = param.search;
                    this.searchSubscription = this.userService.searchByUsername(this.search).subscribe(
                        res => {
                            this.userModel = res;
                        },
                        () => {
                            this.userModel = null;
                        });
                } else {
                    this.getAllSubscription = this.userService.getAll().subscribe(
                        res => {
                            this.userModel = res;
                        },
                        () => {
                            this.userModel = null;
                        }
                    );
                }
            });
    }

    ngOnDestroy(): void {
        this.routSubscription.unsubscribe();
        if (this.searchSubscription) {
            this.searchSubscription.unsubscribe();
        }
        if (this.getAllSubscription) {
            this.getAllSubscription.unsubscribe();
        }
    }
}
