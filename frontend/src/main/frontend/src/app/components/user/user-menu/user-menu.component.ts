import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {User} from "../../../models/user";

@Component({
    selector: 'app-user-menu',
    templateUrl: './user-menu.component.html',
    styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit, OnChanges {
    @Input() user: User;
    @Input() currentUser: User;
    @Output() toOpen = new EventEmitter<string>();

    isOpen: string;

    isAllowedToChange: boolean;
    isAllowedToAdd: boolean;
    isAllowedToDeactivate: boolean;

    constructor() {
        this.open('View');
    }

    ngOnInit() {
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.user = changes.user.currentValue;
        this.isAllowedToDeactivate = this.isAllowedToChange = this.currentUser.userRole == 'super' && this.user.userRole != 'user' || (this.currentUser.userRole == 'admin' && this.user.userRole == 'moderator');
        this.isAllowedToAdd = this.user.id == this.currentUser.id && (this.currentUser.userRole == 'super' || this.currentUser.userRole == 'admin');
    }

    open(tab: string) {
        this.isOpen = tab;
        this.toOpen.emit(tab);
    }
}
