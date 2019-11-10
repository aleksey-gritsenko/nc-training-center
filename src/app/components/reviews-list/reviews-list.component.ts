import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Review} from '../../models/review'
import {CommonService} from '../../services/common/common.service'
@Component({
  selector: 'app-reviews-list',
  templateUrl: './reviews-list.component.html',
  styleUrls: ['./reviews-list.component.css']
})
export class ReviewsListComponent implements OnInit {

  constructor() { }

  ngOnInit() {

  }


}
