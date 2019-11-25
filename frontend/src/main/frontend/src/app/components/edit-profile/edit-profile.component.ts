import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {StorageService} from "../../services/storage/storage.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-edit-profile',
    templateUrl: './edit-profile.component.html',
    styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit, OnDestroy {
    repeatPassword: string = '';

    private currentUser: User;
    private updatedUser: User = new User();

    private subscription: Subscription;

    constructor(private userService: UserService,
                private route: ActivatedRoute,
                private storageService: StorageService) {
    }

    ngOnInit() {
        this.subscription = this.storageService.currentUser.subscribe(user => {
            this.currentUser = user;
            Object.assign(this.updatedUser, this.currentUser);
        });

    }

    update(): void {
        if (this.userService.equals(this.updatedUser, this.currentUser)) return;
        this.userService.updateProfile(this.updatedUser)
            .subscribe(
                user => {
                    this.storageService.setUser(user);
                },
                error => console.log(error)
            );
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }
}
