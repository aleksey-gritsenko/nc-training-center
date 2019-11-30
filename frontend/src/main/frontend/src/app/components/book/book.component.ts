import {Component, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";
import {FormControl, FormGroup} from "@angular/forms";
import {UserBook} from "../../models/userBook";
import {StorageService} from "../../services/storage/storage.service";

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

    userAddedBook: boolean = true;
    userAddedToRead: boolean = true;
    userAddedToFav: boolean = true;

    userBook:UserBook = new UserBook();
    bookParamMap = this.route.snapshot.paramMap;
    constructor(private apiService: CommonService, private route: ActivatedRoute, private router: Router,
                private storage: StorageService) {
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
        this.bookId = parseInt(this.bookParamMap.get('bookId'));
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


    addBookToUser(bookId:number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }
        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;
        this.userAddedBook = false;

        this.apiService.addBookToUser(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Add  book error");
            }
        );
    }

    deleteBookFromUser(bookId:number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }
        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;
        this.userAddedBook = true;

        this.apiService.deleteFromAdded(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Add  book error");
            }
        );
    }

    addBookToFavourite(bookId: number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }

        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToFav = false;

        this.apiService.markUserBookAsFavourite(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Add to FAV book error");
            }
        );
    }

    addBookToRead(bookId: number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }

        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToRead = false;

        this.apiService.markUserBookAsRead(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Add to READ book error");
            }
        );
    }

    removeBookFromFav(bookId: number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }

        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToFav = true;

        this.apiService.removeFromFavourite(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Remove from FAV book error");
            }
        );
    }

    removeBookFromRead(bookId: number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }

        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToRead = true;

        this.apiService.removeFromRead(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Remove from READ book error");
            }
        );
    }

}

