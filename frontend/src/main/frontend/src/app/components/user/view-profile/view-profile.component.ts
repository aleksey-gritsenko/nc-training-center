import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {User} from "../../../models/user";
import {UserService} from "../../../services/user/user.service";
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-view-profile',
    templateUrl: './view-profile.component.html',
    styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit, OnChanges {
    @Input() user: User;
    currentUser: User;
    isAbleToAddToFriend: boolean;

    constructor(private userService: UserService,
                private router: Router,
                private storageService: StorageService) {
    }

    ngOnInit() {
        this.currentUser = this.storageService.getUser();
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.user = changes.user.currentValue;
        if (this.currentUser) {
            this.isAbleToAddToFriend = this.user.id != this.currentUser.id; //TODO Add function for checking friends
        }
    }

    sendRequest() {
        this.userService.sendRequest(this.currentUser.id, this.user.id).subscribe(
            res => {
            },
            err => {
                alert("Error in add friends");
            }
        );
    }
}
