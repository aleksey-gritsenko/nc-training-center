import {Component, Input, OnInit} from '@angular/core';
import {CommonService} from "../../services/common/common.service";
import {User} from "../../models/user";
import {StorageService} from "../../services/storage/storage.service";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-friends-list',
    templateUrl: './friends-list.component.html',
    styleUrls: ['./friends-list.component.css']
})
export class FriendsListComponent implements OnInit {
    userFriends :User[]=[];
    newApplications:User[]=[];

    currentUser:User;
    id:string;

    constructor(private apiService: CommonService,private storage: StorageService,private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        //this.activatedRoute.snapshot.paramMap.get('id');
        this.currentUser = this.storage.getUser();
        this.id = this.activatedRoute.snapshot.paramMap.get('id');
        this.getNewApplications(this.id);
        this.getAllFriends(this.id);

    }
    getAllFriends(id:string){
        this.apiService.getFriends(id).subscribe(
            res => {
                this.userFriends = res;
            },
            err => {
                alert("Error in getting new friends");
            })
    }
    getNewApplications(id:string){
        this.apiService.getNewApplications(id).subscribe(
            res => {
                this.newApplications = res;
            },
            err => {
                alert("Error in getting all friends");
            })
    }
    addFriends(friend:User){
this.apiService.addFriend(friend,this.currentUser)
    }


}
