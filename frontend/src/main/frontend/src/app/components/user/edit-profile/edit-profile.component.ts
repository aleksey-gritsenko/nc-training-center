import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {User} from "../../../models/user";
import {UserService} from "../../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-edit-profile',
    templateUrl: './edit-profile.component.html',
    styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit, OnDestroy {
    repeatPassword: string = '';
    editStatus: string;

    @Input() user: User;
    @Output() userChange = new EventEmitter();

    private updatedUser: User = new User();
    private subscription: Subscription;

    constructor(private userService: UserService,
                private route: ActivatedRoute,
                private storageService: StorageService) {
    }

    ngOnInit() {
        Object.assign(this.updatedUser, this.user);
    }

    update(): void {
        this.editStatus = '';
        // if (this.userService.equals(this.updatedUser, this.user)) return;
        this.subscription = this.userService.updateProfile(this.updatedUser)
            .subscribe(
                user => {
                    this.editStatus = 'success';
                    this.repeatPassword = '';
                    this.userChange.emit(user);
                },
                error => {
                    this.editStatus = 'error';
                    Object.assign(this.updatedUser, this.user);
                    this.repeatPassword = '';
                });
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }
}
