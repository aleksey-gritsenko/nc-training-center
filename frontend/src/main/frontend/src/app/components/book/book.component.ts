import {Component, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute} from "@angular/router";
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";

@Component({
    selector: 'app-book',
    templateUrl: './book.component.html',
    styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
    book: Book = new Book();
    genre: Genre = new Genre();
    authors: Author[] = [];
    constructor(private apiService: CommonService, private route: ActivatedRoute) {
    }

    id: any;

    ngOnInit() {
        this.id = parseInt(this.route.snapshot.paramMap.get('id'));
        this.getGenreByBookId();
        this.getBook();
        this.getAuthorByBookId();
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

    getGenreByBookId(){
        this.apiService.getGenreByBookId(this.id).subscribe(
            res=>{this.genre  = res;
            console.log(this.genre)},
            err=>{
                alert("Error in get genres by book id")
            }
        );
    }

    getAuthorByBookId(){
        this.apiService.getAuthorsByBookId(this.id).subscribe(
            res=>{this.authors = res;
            console.log(this.authors)},
            err=>{
                alert("Error in get author by book id")
            }
        )
    }

    getAuthors(){

    }
}

