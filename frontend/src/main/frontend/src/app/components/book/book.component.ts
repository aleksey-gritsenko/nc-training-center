import {Component, Input, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Author} from "../../models/author";
import {BookFilter} from "../../models/bookfilter";
import {StorageService} from "../../services/storage/storage.service";
import {UserBook} from "../../models/userBook";

@Component({
    selector: 'app-book',
    templateUrl: './book.component.html',
    styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
    @Input() book: Book;
    authors: Author[] = [];
    suggestionBook: Book[] = [];
    bookId: any;

    userBook: UserBook = {
        bookId: 0,
        userId: 0,
        isFavorite: false,
        isRead: false
    };

    addAnnouncementVisible: boolean = false;
    userBookButtonVisible: boolean = false;


    userAddedBook: boolean;
    userAddedToRead: boolean;
    userAddedToFav: boolean;


    constructor(private apiService: CommonService,
                private route: ActivatedRoute,
                private router: Router,
                private storage: StorageService) {
    }

    ngOnInit() {
        this.bookId = parseInt(this.route.snapshot.paramMap.get('bookId'));
        this.getBook(this.bookId);
        this.checkUser();
        this.makeSuggestion();
    }


    checkUser(){
        if (this.storage.getUser()!=null) {
            this.addAnnouncementVisible = true;
            this.userBookButtonVisible = true;
        }
    }

    getBook(bookId: number): void {
        this.apiService.getBookById(bookId).subscribe(
            res => {
                this.book = res;
                this.apiService.getGenreByBookId(this.bookId).subscribe(
                    genre => {
                        this.book.genre = genre.name;
                    });
                this.apiService.getAuthorsByBookId(this.bookId).subscribe(
                    authors => {
                        this.book.authors = [];
                        this.authors = authors;
                        authors.forEach(author => this.book.authors.push(author));
                    }
                );
                this.apiService.getImageByBook(this.book).subscribe(
                    img => {
                        let reader = new FileReader();
                        reader.addEventListener("load", () => {
                            this.book.photoURL = reader.result;
                        }, false);
                        if (img) {
                            reader.readAsDataURL(img);
                        }
                    }
                )
            },
            err => {
                this.router.navigateByUrl('/error');
            }
        );
    }

    makeSuggestion() {

        let suggestionFilter: BookFilter = this.storage.getFilter();

        if (this.storage.getUser() != null) {
            this.apiService.makeSuggestion(this.storage.getUser().id).subscribe(
                books => {
                    this.suggestionBook = books || [];
                }
            );
            if(suggestionFilter.author!=[]||suggestionFilter.header!=""||suggestionFilter.genre!=[]){
                this.apiService.getBooksByFilter(suggestionFilter).subscribe(
                    books => {
                        this.suggestionBook.push(...(books || []));
                    }
                );
            }
        }
        this.apiService.getMostRatedBooks().subscribe(
            books => {
                this.suggestionBook.push(...(books || []));
                this.suggestionBook = this.suggestionBook.filter(
                    (thing, i, arr) => arr.findIndex(t => t.id === thing.id) === i
                );
            }
        );
    }

    addBookToUser(bookId: number) {
        this.checkPresentUser();
        this.apiService.getUserBookById(this.storage.getUser().id, bookId).subscribe(
            res => {
                this.userBook = res;
            }
        );
        /*        if (this.userBook != undefined){
                    this.userAddedBook = true;
                } else {*/
        let userBook: UserBook = new UserBook();
        userBook.bookId = bookId;
        userBook.userId = this.storage.getUser().id;


        this.apiService.addBookToUser(userBook).subscribe(
            res => {
                console.log("add book: "+ JSON.stringify(res));
            },
            err => {
                console.log("Add  book error");
            }
        );
        //}
    }

    deleteBookFromUser(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;
        this.userAddedBook = false;
        this.apiService.deleteFromAdded(userBook).subscribe(
            res => {
                console.log(res);
            },
            err => {
                console.log("Add  book error");
            }
        );
    }

    addBookToFavourite(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToFav = true;
        this.apiService.markUserBookAsFavourite(userBook).subscribe(
            res => {
                console.log("add to fav book: "+ JSON.stringify(res));
            },
            err => {
                console.log("Add to FAV book error");
            }
        );
    }

    addBookToRead(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;
        this.userAddedToRead = true;
        this.apiService.markUserBookAsRead(userBook).subscribe(
            res => {
                console.log("add to read book: "+ JSON.stringify(res));
            },
            err => {
                console.log("Add to READ book error");
            }
        );
    }

    removeBookFromFav(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToFav = false;
        this.apiService.removeFromFavourite(userBook).subscribe(
            res => {
                console.log(res);

            },
            err => {
                console.log("Remove from FAV book error");
            }
        );
    }

    removeBookFromRead(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToRead = false;
        this.apiService.removeFromRead(userBook).subscribe(
            res => {
                console.log(res);

            },
            err => {
                console.log("Remove from READ book error");
            }
        );
    }

    navigateToBook(bookId: number) {
        this.router.navigate(['books/book', bookId]);
        this.suggestionBook = [];
        this.ngOnInit();
    }

    checkPresentUser() {
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }
    }
}

