import {ActivatedRoute} from "@angular/router";
import {Component, Input, OnInit} from '@angular/core';
import {Announcement} from '../../models/announcement';
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-announcement',
    templateUrl: './announcement.component.html',
    styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit {
    model : Announcement = {
        title:'',
        description:'',
        date:'',
        id:0
    };
    constructor(private http: HttpClient,private route: ActivatedRoute) {
    }
    id:any;

    /*
      constructor(private route: ActivatedRoute) { }
      id:any;
      ngOnInit() {
        this.id = parseInt(this.route.snapshot.paramMap.get('id'));
     */

    ngOnInit() {
        this.model
        this.id
            = parseInt(this.route.snapshot.paramMap.get('id'));
    }

    createAnnouncement(): void {
        let url = 'http://localhost:8080/myannouncement';
        this.model.id = this.id;
        this.http.post(url, this.model).subscribe(
            res=>{
                location.reload();
            },
            err=>{
                err => {alert(JSON.parse(JSON.stringify(err)).message);}      }
        );

    }

}
