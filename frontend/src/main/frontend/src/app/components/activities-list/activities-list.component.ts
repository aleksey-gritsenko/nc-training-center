import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../services/storage/storage.service";
import {Book} from "../../models/book";
import {HttpClient} from "@angular/common/http";
import {CommonService} from "../../services/common/common.service";
import {userSearch} from "../../models/userSearch";
import {UserService} from "../../services/user/user.service";
import {Achievement} from "../../models/achievement";
import {Observable} from 'rxjs';
@Component({
    selector: 'app-activities-list',
    templateUrl: './activities-list.component.html',
    styleUrls: ['./activities-list.component.css']
})
export class ActivitiesListComponent implements OnInit {
    private localhost: string = 'http://localhost:8080';
    private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';

    achievements:Achievement = new Achievement();

    constructor(private storage: StorageService,
                private http: HttpClient,
                private userService:UserService) {
    }

    ngOnInit() {
       if(this.storage.getUser()!=null){
           this.getAllActivitiesByUserId(this.storage.getUser().id).subscribe(
               res=>{
                   this.achievements.description.push(...res||[]);
                   console.log(this.achievements.description)
               }
           )
       }
    }


    getAllActivitiesByUserId(userId:number):Observable<string[]>{
        //const url = `${this.siteUrl}/notification/getUser/userId?userId=${userId}`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/notification/getUser/${userId}`;
        console.log(url);
        return this.http.get<string[]>(url);
    }
}
