import {Component, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute} from "@angular/router";
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-book',
    templateUrl: './book.component.html',
    styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
    book: Book = new Book();
    authors: Author[] = [];
    updatedBook: FormGroup;
    bookId: any;
    constructor(private apiService: CommonService, private route: ActivatedRoute) {
    }

    bookForm = new FormGroup({
        header: new FormControl(''),
        genre: new FormControl(''),
        status: new FormControl(''),
        overview: new FormControl(''),
        file: new FormControl(''),
        author: new FormControl('')
    });

    ngOnInit() {
        this.bookForm.disable();
        this.bookId = parseInt(this.route.snapshot.paramMap.get('bookId'));
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
        this.apiService.getBookById(this.bookId).subscribe(
            res => {
                this.book = res;
                this.apiService.getGenreByBookId(this.bookId).subscribe(
                    genre=>{
                        this.book.genre = genre.name;
                    });
                this.apiService.getAuthorsByBookId(this.bookId).subscribe(
                    authors=>{
                        this.book.authors = [];
                        this.authors = authors;
                        authors.forEach(author=>this.book.authors.push(author));
                    }
                );
            },
            err => {
                alert("Error in get book by id")
            }
        );
    }

}

