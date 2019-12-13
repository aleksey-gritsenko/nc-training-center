import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {User} from "../../../models/user";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-user-menu',
    templateUrl: './user-menu.component.html',
    styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit, OnChanges {
    @Input() user: User;
    currentUser: User;
    @Output() toOpen = new EventEmitter<string>();

    isOpen: string;

    isAllowedToChange: boolean;
    isAllowedToAdd: boolean;
    isAllowedToDeactivate: boolean;
    isAllowedToPublish: boolean;

    constructor(private storageService: StorageService) {
        this.open('View');
        this.currentUser = this.storageService.getUser()
    }

    ngOnInit() {
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.user = changes.user.currentValue;
        if (this.user.id) {
            if (this.user.id == this.currentUser.id) {
                this.isAllowedToDeactivate = false;
                this.isAllowedToAdd = this.currentUser.userRole == 'super' || this.currentUser.userRole == 'admin';
                this.isAllowedToChange = this.currentUser.userRole == 'user';
                this.isAllowedToPublish = this.currentUser.userRole == 'moderator';
            } else {
                this.isAllowedToAdd = this.isAllowedToPublish = false;
                this.isAllowedToDeactivate = this.isAllowedToChange = this.currentUser.userRole == 'super' && this.user.userRole != 'user' || (this.currentUser.userRole == 'admin' && this.user.userRole == 'moderator');
            }
        }
    }

    open(tab: string) {
        this.isOpen = tab;
        this.toOpen.emit(tab);
    }
}
