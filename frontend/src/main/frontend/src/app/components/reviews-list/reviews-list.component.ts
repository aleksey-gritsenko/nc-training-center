import {Component, DoCheck, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Review} from '../../models/review'
import {CommonService} from '../../services/common/common.service'
import {Book} from "../../models/book";

@Component({
    selector: 'app-reviews-list',
    templateUrl: './reviews-list.component.html',
    styleUrls: ['./reviews-list.component.css']
})
export class ReviewsListComponent implements OnInit{

    acceptedReviews: Review[] = [];
    notAcceptedReviews: Review[] = [];
    @Input() book: Book;
    addReviewVisible: boolean;

    createdReview: Review = new Review();

    constructor(private commonService: CommonService, private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.addReviewVisible = false;
        this.ngOnChanges();
    }


    ngOnChanges() {
        if(this.book.id!=null){
            this.getAcceptedReviews();
            this.getNotAcceptedReviews();
        }
    }
    getAcceptedReviews(): void {
        this.commonService.getAcceptedReviews(this.book.id, true).subscribe(
            res => {
                this.acceptedReviews = res;
            }
        )
    }

    getNotAcceptedReviews(): void{
        this.commonService.getAcceptedReviews(this.book.id, false).subscribe(
            res => {
                this.notAcceptedReviews = res;
            }
        )
    }

    acceptReview(review:Review):void{
        review.adminId=1;
        this.commonService.acceptReview(review, true).subscribe(
            res=>{this.acceptedReviews.push(review);
                this.notAcceptedReviews.splice(this.notAcceptedReviews.indexOf(review));
            }
        );
    }
    createReview(): void {
        const newCreatedReview: Review = Object.assign({}, this.createdReview);
        newCreatedReview.bookId = this.book.id;
        this.commonService.createReview(newCreatedReview)
            .subscribe(res => {
                    this.notAcceptedReviews.push(res)
                },
                err => {
                    alert("Error in creating new review");
                }
            );
    }

    deleteReviewById(review:Review){
        this.commonService.deleteReviewById(review).subscribe(
            res=>{this.notAcceptedReviews.splice(this.notAcceptedReviews.indexOf(review),1)}
        );
    }
}
