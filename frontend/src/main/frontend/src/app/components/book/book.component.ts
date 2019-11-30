import {Component, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute} from "@angular/router";
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";
import {FormControl, FormGroup} from "@angular/forms";
import {BookFilter} from "../../models/bookfilter";
import {StorageService} from "../../services/storage/storage.service";
import { Router } from '@angular/router'

@Component({
    selector: 'app-book',
    templateUrl: './book.component.html',
    styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
    book: Book = new Book();
    authors: Author[] = [];
    suggestionBook:Book[] = [];
    bookId: any;
    constructor(private apiService: CommonService,
                private route: ActivatedRoute,
                private storage:StorageService,
                private  router: Router) {
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
        this.checkAdmin();
        this.bookId = parseInt(this.route.snapshot.paramMap.get('bookId'));
        this.getBook();
        this.makeSuggestion();
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

    //TODO
    checkAdmin(){
        this.bookForm.enable();
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


    makeSuggestion(){
        let authors = JSON.parse(localStorage.getItem('authors'));
        let genres = JSON.parse(localStorage.getItem('genres'));

        let suggestionFilter:BookFilter = new BookFilter();
        suggestionFilter.genre = genres;
        suggestionFilter.author = authors;

        if(this.storage.getUser()!=null)
        {
            this.apiService.makeSuggestion(this.storage.getUser().id).subscribe(
                books=>{this.suggestionBook=books||[];}
            );
            this.apiService.getBooksByFilter(suggestionFilter).subscribe(
                books=>{
                    this.suggestionBook.push(...(books||[]));
                }
            );
        }
        this.apiService.getMostRatedBooks().subscribe(
            books=>{
                this.suggestionBook.push(...(books||[]));
                this.suggestionBook = this.suggestionBook.filter(
                    (thing, i, arr) => arr.findIndex(t => t.id === thing.id) === i
                );
            }
        );


    }

    navigateToBook(bookId: number){
        this.router.navigate(['books/book', bookId]);
        this.suggestionBook = [];
        this.ngOnInit();
    }

}

