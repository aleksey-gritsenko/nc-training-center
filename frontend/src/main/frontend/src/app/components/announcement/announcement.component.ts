import {ActivatedRoute} from "@angular/router";
import {Component, Input, OnInit} from '@angular/core';
import {Announcement} from '../../models/announcement';
import {HttpClient} from "@angular/common/http";
import {StorageService} from "../../services/storage/storage.service";
import {User} from "../../models/user";
import {CommonService} from "../../services/common/common.service";


@Component({
    selector: 'app-announcement',
    templateUrl: './announcement.component.html',
    styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit {

    private siteUrl: string = 'https://nc-group1-2019.herokuapp.com';

    role: String;
    model: Announcement = {
        id: 0,
        description: '',
        announcementDate: new Date(),
        bookID: 0,
        ownerId: 0,
        admin_id: 0,
        status: 'UNPUBLISHED'
    };
    currentUser: User;
    id: any;
    currentDate : Date;


    constructor(private http: HttpClient, private route: ActivatedRoute,
                private storage: StorageService, private apiService: CommonService) {
    }

    ngOnInit() {
        this.currentDate = new Date();
        this.model
        this.id
            = parseInt(this.route.snapshot.paramMap.get('id'));
        this.storage
        //this.model
        //this.id = parseInt(this.route.snapshot.paramMap.get('id'));
        //this.storage
        this.currentUser = this.storage.getUser();
        this.currentDate = new Date()
    }

    compareDate(date: Date): boolean {
        return date > this.currentDate;
    }

    createAnnouncement(): void {
        let url = `${this.siteUrl}/announcements/proposeAnnouncement`;
        this.model.bookID = this.id;
        if (this.currentUser.userRole == 'admin') {

            this.model.status = 'PUBLISHED';
            this.model.admin_id = this.currentUser.id;
        }
        this.model.ownerId = this.currentUser.id;
        // this.model.ownerId = this.storage.getUser().id;

        this.apiService.createAnnouncement(this.model).subscribe(
            res => {
                //location.reload();
            },
            err => {
                alert(JSON.parse(JSON.stringify(err)).message);
            }
        );
    }
}
