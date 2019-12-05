import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../services/common/common.service";
import {User} from "../../models/user";
import {StorageService} from "../../services/storage/storage.service";

@Component({
    selector: 'app-friends-list',
    templateUrl: './friends-list.component.html',
    styleUrls: ['./friends-list.component.css']
})
export class FriendsListComponent implements OnInit {
    userFriends :User[]=[];
    newApplications:User[]=[];
    currentUser:User;
    constructor(private apiService: CommonService,private storage: StorageService) {
    }

    ngOnInit() {
       // this.currentUser = this.storage.getUser();
        this.getNewApplications();
        this.getAllFriends();
    }
    getAllFriends(){
        this.apiService.getFriends().subscribe(
            res => {
                this.userFriends = res;
            },
            err => {
                alert("Error in getting new friends");
            })
    }
    getNewApplications(){
        this.apiService.getNewApplications().subscribe(
            res => {
                this.newApplications = res;
            },
            err => {
                alert("Error in getting all friends");
            })
    }
    addFriends(){

    }


}
