import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Review} from '../../models/review'
import {CommonService} from '../../services/common/common.service'
import {Book} from "../../models/book";
import {UserService} from "../../services/user/user.service";
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import {StorageService} from "../../services/storage/storage.service";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
    selector: 'app-reviews-list',
    templateUrl: './reviews-list.component.html',
    styleUrls: ['./reviews-list.component.css']
})
export class ReviewsListComponent implements OnInit {
    public Editor = ClassicEditor;
    acceptedReviews: Review[] = [];
    notAcceptedReviews: Review[] = [];
    @Input() book: Book;
    addReviewVisible: boolean;

    successCreatedReview:boolean = true;
    errorCreatedReview:boolean = true;
    createdReview: Review = new Review();
    notAcceptedReviewVisible:boolean = false;
    createdReviewForm = this.formBuilder.group({
        grade: [5, Validators.required],
        text: ['', Validators.required]
    });

    constructor(private commonService: CommonService,
                private route: ActivatedRoute,
                private router: Router,
                private  userService: UserService,
                private storage: StorageService,
                private formBuilder: FormBuilder) {
    }


    ngOnInit() {
        this.addReviewVisible = false;
        this.ngOnChanges();
    }

    checkAdmin(){
        if (this.storage.getUser().userRole == 'moderator') {
            this.notAcceptedReviewVisible = true;
        }
    }

    ngOnChanges() {
        if (this.book != null) {
            this.getAcceptedReviews();
            this.getNotAcceptedReviews();
            this.checkAdmin();
        }
    }

    getAcceptedReviews(): void {
        this.commonService.getAcceptedReviews(this.book.id).subscribe(
            res => {
                this.acceptedReviews = res;
                this.acceptedReviews.forEach(
                    review => {
                        this.userService.searchUser(review.userId.toString()).subscribe(
                            res => {
                                review.username = res.userName;
                            });
                    }
                );
            }
        );
    }

    getNotAcceptedReviews(): void {
        this.commonService.getNotAcceptedReviews(this.book.id).subscribe(
            res => {
                this.notAcceptedReviews = res;
                this.notAcceptedReviews.forEach(
                    review => {
                        this.userService.searchUser(review.userId.toString()).subscribe(
                            res => {
                                review.username = res.userName;
                            });
                    }
                );
            }
        )
    }

    acceptReview(review: Review): void {
        this.checkUser();
        review.adminId = this.storage.getUser().id;
        this.commonService.acceptReview(review).subscribe(
            res => {
                this.acceptedReviews.push(review);
                this.notAcceptedReviews.splice(this.notAcceptedReviews.indexOf(review));
            }
        );
    }

    createReview(): void {
        this.checkUser();
        const newCreatedReview: Review = Object.assign({}, this.createdReview);
        newCreatedReview.bookId = this.book.id;
        newCreatedReview.userId = this.storage.getUser().id;
        this.commonService.createReview(newCreatedReview)
            .subscribe(res => {
                    this.successCreatedReview = true;
                    this.userService.searchUser(res.userId.toString()).subscribe(
                        user => {
                            res.username = user.userName;
                        }
                    );
                    this.createdReview.text = "";
                    this.createdReview.grade = null;
                    this.notAcceptedReviews.push(res);
                },
                err => {
                    this.errorCreatedReview = true;
                }
            );

    }

    deleteReviewById(review: Review) {
        this.commonService.deleteReviewById(review).subscribe(
            res => {
                this.notAcceptedReviews.splice(this.notAcceptedReviews.indexOf(review), 1)
            }
        );
    }

    fillArray(grade: number) {
        return Array.from({length: grade}, (v, i) => i)
    }

    checkUser() {
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }
    }
}
