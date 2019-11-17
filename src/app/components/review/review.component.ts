import {Component, Input, OnInit} from '@angular/core';
import {Review} from "../../models/review";
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  // do we need only one review?
  @Input() reviews:Review[];
  @Input() review:Review;

  constructor(private commonService:CommonService,private route: ActivatedRoute) { }

  ngOnInit() {
    this.getReview();
  }

  getReview():void{
    const id = + this.route.snapshot.paramMap.get('id');
    this.commonService.getReviews(id).subscribe(reviews=>this.reviews=reviews);
  }

}
