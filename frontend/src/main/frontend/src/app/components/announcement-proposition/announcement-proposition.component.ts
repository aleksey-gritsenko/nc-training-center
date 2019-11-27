import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../models/announcement";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-announcement-proposition',
    templateUrl: './announcement-proposition.component.html',
    styleUrls: ['./announcement-proposition.component.css']
})
export class AnnouncementPropositionComponent implements OnInit {

    announcements: Announcement[] = [];

    constructor(private http: HttpClient,private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.getAllAnnouncement();
    }

    public getAllAnnouncement() {
        let url = 'http://localhost:8080//announcements//new';
        this.http.get<Announcement[]>(url).subscribe(
            res => {
                this.announcements = res;
            },
            err => {
                alert("ERROr");
            }
        )
    }
    public publishAnnouncement(announcement : Announcement){
        let url = 'http://localhost:8080//announcements//publish';

        // this.model.ownerId = this.storage.getUser().id;
        this.http.post(url, announcement).subscribe(
            res=>{
                //location.reload();
            },
            err=>{
                err => {alert(JSON.parse(JSON.stringify(err)).message);}      }
        );
    }
}
