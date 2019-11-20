import {Component, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-book',
    templateUrl: './book.component.html',
    styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
    book: Book = new Book();

    constructor(private apiService: CommonService, private route: ActivatedRoute) {
    }

    id: any;

    ngOnInit() {
        this.id = parseInt(this.route.snapshot.paramMap.get('id'));
        this.getBook();
    }

    updateBook(): void {
        const newCreatedBook: Book = Object.assign({}, this.book);
        this.apiService.updateBook(newCreatedBook).subscribe(
            res => {
                this.book = res;
                console.log(res);
            }
        );
    }

    getBook(): void {
        console.log(this.id);
        this.apiService.getBookById(this.id).subscribe(
            res => {
                this.book = res;
                console.log(this.book);
            },
            err => {
                alert("Error in get book by id")
            }
        );
    }
}

/*
  getBooks():void{
    this.apiService.getBooks().subscribe(
      res=>{
        this.books = res;
        console.log(this.books);
      },
      err=>{alert("Error in get all reviews")}
    );
  }
*/
