import {Component, Input, OnInit} from '@angular/core';
import {CommonService} from '../../services/common/common.service';
import {Book} from '../../models/book'
import {ActivatedRoute, Router} from '@angular/router';
import {BookFilter} from '../../models/bookfilter';
import {SelectedItem} from '../../models/selected-item-filter';
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";
import {UserBook} from "../../models/userBook";
import {StorageService} from "../../services/storage/storage.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-books-list',
    templateUrl: './books-list.component.html',
    styleUrls: ['./books-list.component.css']
})

export class BooksListComponent implements OnInit {

    genres: Genre[] = [];
    authors: Author[] = [];
    books: Book[] = [];

    userBooks: UserBook[] = [];
    @Input() userBookList: Book[] = [];
    @Input() userFavBookList: Book[] = [];
    @Input() userReadBookList: Book[] = [];
    book: Book;

    bookFilter: BookFilter = new BookFilter();
    addBookVisible: boolean = false;

    emptyBookList: boolean = false;
    emptyFavList: boolean = false;
    emptyReadList: boolean = false;

    selectedAuthors: SelectedItem[] = [];
    selectedGenres: SelectedItem[] = [];
    filterGenres: SelectedItem[] = [];
    filterAuthors: SelectedItem[] = [];
    historyFilter: BookFilter;
    searchGenre;
    searchAuthor;
    userId: any;

    private subscription: Subscription;

    constructor(private apiService: CommonService,
                private route: ActivatedRoute,
                private router: Router,
                private storage: StorageService) {

    }

    ngOnInit() {
        this.checkUser();
        this.getBooks();
        this.getAllAuthor();
        this.getAllGenre();
        this.checkHistoryFilter();
    }

    checkHistoryFilter(){
        this.historyFilter = this.storage.getFilter();
    }

    checkUser(){
        if (this.storage.getUser() != null) {
            this.getUsersBookList();
            this.getAllReadBooks();
            this.getAllFavouriteBooks();
            this.checkModerator();
        }
    }

    checkModerator(){
        if(this.storage.getUser().userRole=='moderator'){
            this.addBookVisible = true;
        }
    }

    onFilterChange(event: any){
        this.searchByFilter();
    }

    getUsersBookList() {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        this.subscription = this.apiService.getAllUserBooks(userBook).subscribe(
            res => {
                this.userBookList = res;
                if (this.userBookList.length == 0) {
                    this.emptyBookList = true;
                    this.subscription = this.apiService.getMostRatedBooks().subscribe(
                        bookList => {
                            this.userBookList = bookList;
                        }
                    );
                }
            },
        );
    }

    getAllFavouriteBooks() {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        this.subscription = this.apiService.getAllFavouriteBooks(userBook).subscribe(
            res => {
                this.userFavBookList = res;
                if (this.userFavBookList.length == 0) {
                    this.emptyFavList = true;
                    this.subscription = this.apiService.getMostRatedBooks().subscribe(
                        favList => {
                            this.userFavBookList = favList;
                        }
                    );
                }
            },
        );
    }

    getAllReadBooks() {
        this.checkPresentUser();

        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        this.subscription = this.apiService.getAllReadBooks(userBook).subscribe(
            res => {
                this.userReadBookList = res;
                if (this.userReadBookList.length == 0) {
                    this.emptyReadList = true;
                    this.subscription = this.apiService.getMostRatedBooks().subscribe(
                        readList => {
                            this.userReadBookList = readList;
                        }
                    );
                }
            },
        );
    }

    getAllAuthor() {
        this.apiService.getAllAuthor().subscribe(
            res => {
                this.authors = res;
                this.authors.forEach(author => {
                    this.selectedAuthors.push({name: author.name, selected: false});
                });

            },
            err => {
                console.log(err)
            }
        );
    }

    getAllGenre() {
        this.apiService.getAllGenre().subscribe(
            res => {
                this.genres = res;
                this.genres.forEach(genre => {
                    this.selectedGenres.push({name: genre.name, selected: false});
                });
            },
            err => {
                console.log(err)
            }
        );

    }

    searchByFilter() {
        this.bookFilter.author = [];
        this.bookFilter.genre = [];

        this.filterGenres = this.selectedGenres
            .filter(v => v.selected);
        this.filterAuthors = this.selectedAuthors
            .filter(v => v.selected);

        this.filterGenres.forEach(genre => {
            this.bookFilter.genre.push(genre.name);
        });
        this.filterAuthors.forEach(author => {
            this.bookFilter.author.push(author.name);
        });


        this.historyFilter.genre.push(...(this.bookFilter.genre || []));
        this.historyFilter.author.push(...(this.bookFilter.author || []));
        this.storage.setFilter(this.historyFilter);
        this.books = [];
        this.apiService.getBooksByFilter(this.bookFilter).subscribe(
            res => {
                this.books = res;
                this.books.forEach(book => {
                    this.apiService.getAuthorsByBookId(book.id).subscribe(
                        authors => book.authors = authors
                    );
                    this.apiService.getGenreByBookId(book.id).subscribe(
                        genre => book.genre = genre.name
                    )
                })
            },
            error => console.log('Error in book filter')
        );

    }

    resetFiler() {
        this.bookFilter.header = "";
        this.bookFilter.author = [];
        this.bookFilter.genre = [];
        this.filterGenres = [];
        this.filterAuthors = [];
        this.selectedGenres.forEach(genre => genre.selected = false);
        this.selectedAuthors.forEach(author => author.selected = false);
        this.getBooks();
    }

    getBooks(): void {
        this.apiService.getBooks().subscribe(
            res => {
                this.books = res;
                this.books.forEach(book => {
                    this.apiService.getAuthorsByBookId(book.id).subscribe(
                        authors => book.authors = authors
                    );
                    this.apiService.getGenreByBookId(book.id).subscribe(
                        genre => book.genre = genre.name
                    );
                });

            },
            err => {
                this.router.navigateByUrl('/error');
            }
        );
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






