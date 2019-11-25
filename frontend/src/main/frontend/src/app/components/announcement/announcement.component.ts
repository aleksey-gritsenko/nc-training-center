import {ActivatedRoute} from "@angular/router";
import {Component, Input, OnInit} from '@angular/core';
import {Announcement} from '../../models/announcement';
import {HttpClient} from "@angular/common/http";
//import {Storage} from '../../services/storage';
import {StorageService} from "../../services/storage/storage.service";
@Component({
    selector: 'app-announcement',
    templateUrl: './announcement.component.html',
    styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit {
    role:String;
    model : Announcement = {
    id :0,
    description: '',
    announcementDate: '',
    bookID : 0,
    priority: 'low',
    ownerId : 0,
    status: 'UNPUBLISHED'

};
    constructor(private http: HttpClient,private route: ActivatedRoute,
                private storage: StorageService) {
    }
    id:any;


    ngOnInit() {
        this.model
        this.id
            = parseInt(this.route.snapshot.paramMap.get('id'));
        this.storage
        this.role = 'admin';
    }

    createAnnouncement(): void {
        let url = 'http://localhost:8080//myannouncement';
        this.model.bookID = this.id;
        if(this.role == 'admin'){
            this.model.status = 'PUBLISHED';
        }
       // this.model.ownerId = this.storage.getUser().id;
        this.http.post(url, this.model).subscribe(
            res=>{
                //location.reload();
            },
            err=>{
                err => {alert(JSON.parse(JSON.stringify(err)).message);}      }
        );

    }

}
