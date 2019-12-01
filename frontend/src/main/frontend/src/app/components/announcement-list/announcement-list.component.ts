import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Announcement} from '../../models/announcement';
import {CommonService} from "../../services/common/common.service";

@Component({
    selector: 'app-announcement-list',
    templateUrl: './announcement-list.component.html',
    styleUrls: ['./announcement-list.component.css']
})
export class AnnouncementListComponent implements OnInit {
    announcements: Announcement[] = [];

    constructor(private http: HttpClient, private apiService: CommonService) {
    }

    ngOnInit() {
        this.getAllAnnouncement();
    }

    public getAllAnnouncement() {
        let url = 'http://localhost:8080//announcements';
        this.http.get<Announcement[]>(url).subscribe(
            res => {
                this.announcements = res;
            },
            err => {
                alert("ERROr");
            }
        )
    }

    public getMoreInfo(announcement: Announcement) {
        this.apiService.getBookById(announcement.bookID);

    }
}
