import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }
  id:any;
  ngOnInit() {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
  }

}
