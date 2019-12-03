import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../../services/common/common.service";
import {BooksListComponent} from "../../books-list/books-list.component";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {BookComponent} from "../../book/book.component";

@Component({
    selector: 'app-user-books',
    templateUrl: './user-books.component.html',
    styleUrls: ['./user-books.component.css']
})
export class UserBooksComponent implements OnInit {

    private curBook: BookComponent = new BookComponent(this.apiService, this.route, this.router, this.storage);
    private curBookList: BooksListComponent = new BooksListComponent(this.apiService, this.route, this.router, this.storage);

    constructor(private apiService: CommonService, private route: ActivatedRoute, private router: Router,
                private storage: StorageService) {
    }

    ngOnInit() {
        this.curBookList.getUsersBookList();

        this.curBook.bookForm.disable();
        this.curBookList.getBooks();
        this.curBookList.getAllAuthor();
        this.curBookList.getAllGenre();
    }
}
