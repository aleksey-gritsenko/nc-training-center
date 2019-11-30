import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Announcement} from '../../models/announcement';

@Component({
    selector: 'app-announcement-list',
    templateUrl: './announcement-list.component.html',
    styleUrls: ['./announcement-list.component.css']
})
export class AnnouncementListComponent implements OnInit {

    private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';

    announcements: Announcement[] = [];

    constructor(private http: HttpClient) {
    }

    ngOnInit() {
        this.getAllAnnouncement();
    }

    public getAllAnnouncement() {
        let url = `${this.siteUrl}/all`;
        this.http.get<Announcement[]>(url).subscribe(
            res => {
                this.announcements = res;
            },
            err => {
                alert("Error in getting all announcements");
            }
        )
    }
}
