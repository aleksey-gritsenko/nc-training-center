import {Component, Input, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Author} from "../../models/author";
import {BookFilter} from "../../models/bookfilter";
import {StorageService} from "../../services/storage/storage.service";
import {UserBook} from "../../models/userBook";
import {Subscription} from "rxjs";

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

    addAnnouncementVisible: boolean = false;
    userBookButtonVisible: boolean = false;

    userAddedBook: boolean = false;
    userAddedToRead: boolean = false;
    userAddedToFav: boolean = false;
    userFavReadVisible: boolean = false;

    private subscription: Subscription;

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
            this.checkButton();
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
            this.apiService.getBooksByFilter(suggestionFilter).subscribe(
                books => {
                    this.suggestionBook.push(...(books || []));
                }
            );

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

        let userBook: UserBook = new UserBook();
        userBook.bookId = bookId;
        userBook.userId = this.storage.getUser().id;

        this.userAddedBook = true;
        this.userFavReadVisible = true;
        this.subscription = this.apiService.addBookToUser(userBook).subscribe(
            res => {
                console.log("add book: " + JSON.stringify(res));
            }
        );
    }

    deleteBookFromUser(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;
        this.userAddedBook = false;
        this.userFavReadVisible = false;
        this.subscription = this.apiService.deleteFromAdded(userBook).subscribe(
            res => {
                console.log(res);
            }
        );
    }

    checkButton(){
        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = this.bookId;
        this.subscription = this.apiService.getAllUserBooks(userBook).subscribe(
            res => {
                this.userAddedBook = res.find(book => book.id == this.book.id) != null;
                this.userFavReadVisible = res.find(book => book.id == this.book.id) != null;

            });
        this.subscription = this.apiService.getAllFavouriteBooks(userBook).subscribe(
            res=>{
                this.userAddedToFav = res.find(book => book.id == this.book.id) != null;
                this.userFavReadVisible = res.find(book => book.id == this.book.id) != null;
            }
        );
        this.subscription = this.apiService.getAllReadBooks(userBook).subscribe(
            res=>{
                this.userAddedToRead = res.find(book => book.id == this.book.id) != null;
                this.userFavReadVisible = res.find(book => book.id == this.book.id) != null;
            }
        );
    }

    addBookToFavourite(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToFav = true;
        this.subscription = this.apiService.markUserBookAsFavourite(userBook).subscribe(
            res => {
                console.log("add to fav book: "+ JSON.stringify(res));
            }
        );
    }

    addBookToRead(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToRead = true;
        this.subscription = this.apiService.markUserBookAsRead(userBook).subscribe(
            res => {
                console.log("add to read book: "+ JSON.stringify(res));
            }
        );
    }

    removeBookFromFav(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToFav = false;
        this.subscription = this.apiService.removeFromFavourite(userBook).subscribe(
            res => {
                console.log(res);
            }
        );
    }

    removeBookFromRead(bookId: number) {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToRead = false;
        this.subscription = this.apiService.removeFromRead(userBook).subscribe(
            res => {
                console.log(res);
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

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }
}

