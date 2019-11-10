import { Component, OnInit, Input } from '@angular/core';
import {Book} from '../../models/book';
import{ CommonService } from "../../services/common/common.service";
import {Review} from '../../models/review';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  @Input() book:Book;

  reviews:Review[]=[];
  constructor(private apiService:CommonService) { }



  ngOnInit() {
    this.getReviews();
  }
  goBack(): void {
  }

  updateBook(): void {
    this.apiService.updateBook(this.book);
    window.location.reload();
  }

  getReviews(){
    this.apiService.getReviews(this.book.id).subscribe(
      res=>{
        this.reviews = res;
      },
      err=>{alert("Error in get all reviews")}
    );
  }
}
