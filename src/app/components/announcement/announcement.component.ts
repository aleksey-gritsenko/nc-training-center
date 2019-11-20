  //import { Component, OnInit } from '@angular/core';
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
  model:Announcement = {
    title:'',
    description:'',
    date:'',
    bookId:0
  };
  ann :Announcement;
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
    this.ann

  }

  createAnnouncement(): void {
    let url = 'http://localhost:8080/myannouncement';
    let form = new FormData();
    form.append('title', this.model.title);
    form.append('description', this.model.description);
    form.append('date',this.model.date);
  /* this.ann .title = this.model.title;
    this.ann.date = this.model.date;
    this.ann.description = this.model.description;
    this.ann.bookId = this.id;

   */


   // form.append('bookId',this.model.bookId)
    this.http.post(url, this.model).subscribe(
      res=>{
        location.reload();
      },
             err=>{
               err => {alert(JSON.parse(JSON.stringify(err)).message);}      }
    );

  }

}
