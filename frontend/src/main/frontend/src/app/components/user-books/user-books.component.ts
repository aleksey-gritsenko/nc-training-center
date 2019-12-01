import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../services/common/common.service";
import {BooksListComponent} from "../books-list/books-list.component";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../services/storage/storage.service";
import {UserBook} from "../../models/userBook";
import {BookComponent} from "../book/book.component";
import {Book} from "../../models/book";

@Component({
    selector: 'app-user-books',
    templateUrl: './user-books.component.html',
    styleUrls: ['./user-books.component.css']
})
export class UserBooksComponent implements OnInit {

    private userBook: UserBook = new UserBook();
    private userBookList: Book[] = [];

    private curBook: BookComponent = new BookComponent(this.apiService, this.route, this.router, this.storage);
    private curBookList: BooksListComponent = new BooksListComponent(this.apiService, this.route, this.router, this.storage);
    private bookId: any;

    constructor(private apiService: CommonService, private route: ActivatedRoute, private router: Router,
                private storage: StorageService) {
    }

    ngOnInit() {
        this.curBook.bookForm.disable();
        this.bookId = parseInt(this.route.snapshot.paramMap.get('bookId'));
        this.curBook.getBook();

        this.curBookList.getUsersBookList();
        this.curBookList.getBooks();
        this.curBookList.getAllAuthor();
        this.curBookList.getAllGenre();
    }
}
