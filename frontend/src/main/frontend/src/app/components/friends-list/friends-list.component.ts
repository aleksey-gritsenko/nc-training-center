import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../services/common/common.service";
import {User} from "../../models/user";

@Component({
    selector: 'app-friends-list',
    templateUrl: './friends-list.component.html',
    styleUrls: ['./friends-list.component.css']
})
export class FriendsListComponent implements OnInit {
    userFriends :User[]=[];
    newApplications:User[]=[];
    constructor(private apiService: CommonService) {
    }

    ngOnInit() {
        this.getNewApplications();
        this.getAllFriends();
    }
    getAllFriends(){
        this.apiService.getFriends().subscribe(
            res => {
                this.userFriends = res;
            },
            err => {
                alert("Error in getting all announcements");
            })
    }
    getNewApplications(){
        this.apiService.getNewApplications().subscribe(
            res => {
                this.newApplications = res;
            },
            err => {
                alert("Error in getting all announcements");
            })
    }
    addFriends(){

    }


}
