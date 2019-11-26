import {Component, DoCheck, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Review} from '../../models/review'
import {CommonService} from '../../services/common/common.service'
import {Book} from "../../models/book";
import {UserService} from "../../services/user/user.service";
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
    selector: 'app-reviews-list',
    templateUrl: './reviews-list.component.html',
    styleUrls: ['./reviews-list.component.css']
})
export class ReviewsListComponent implements OnInit{
    public Editor = ClassicEditor;
    acceptedReviews: Review[] = [];
    notAcceptedReviews: Review[] = [];
    @Input() book: Book;
    addReviewVisible: boolean;

    createdReview: Review = new Review();

    constructor(private commonService: CommonService,
                private route: ActivatedRoute,
                private router: Router,
                private  userService:UserService) {
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
                this.acceptedReviews.forEach(
                    review => {
                        this.userService.searchUser(review.userId.toString()).subscribe(
                            res=>{
                                review.username = res.userName;
                            });
                    }
                );
            }
        );
    }

    getNotAcceptedReviews(): void{
        this.commonService.getAcceptedReviews(this.book.id, false).subscribe(
            res => {
                this.notAcceptedReviews = res;
                this.notAcceptedReviews.forEach(
                    review => {
                        this.userService.searchUser(review.userId.toString()).subscribe(
                            res=>{
                                review.username = res.userName;
                            });
                    }
                );
            }
        )
    }

    acceptReview(review:Review):void{
        review.adminId=parseInt(this.route.snapshot.paramMap.get('id'));
        this.commonService.acceptReview(review, true).subscribe(
            res=>{this.acceptedReviews.push(review);
                this.notAcceptedReviews.splice(this.notAcceptedReviews.indexOf(review));
            }
        );
    }
    createReview(): void {
        const newCreatedReview: Review = Object.assign({}, this.createdReview);
        newCreatedReview.bookId = this.book.id;
        newCreatedReview.userId = parseInt(this.route.snapshot.paramMap.get('id'));
        console.log(this.createdReview.text);
        if(isNaN(newCreatedReview.userId))
        {
            this.router.navigate(['/login']);
        }
        else
        {
            this.commonService.createReview(newCreatedReview)
                .subscribe(res => {
                        this.userService.searchUser(res.userId.toString()).subscribe(
                            user=>{res.username = user.userName;}
                        );
                        this.notAcceptedReviews.push(res);
                    },
                    err => {
                        alert("Error in creating new review");
                    }
                );
        }
    }

    deleteReviewById(review:Review){
        this.commonService.deleteReviewById(review).subscribe(
            res=>{this.notAcceptedReviews.splice(this.notAcceptedReviews.indexOf(review),1)}
        );
    }

    fillArray(grade:number){
        return Array.from({ length: grade }, (v, i) => i)
    }
}
