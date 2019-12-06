import {Component, OnInit} from '@angular/core';
import {BookComponent} from "../../book/book.component";
import {BooksListComponent} from "../../books-list/books-list.component";
import {CommonService} from "../../../services/common/common.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-user-read-books',
    templateUrl: './user-read-books.component.html',
    styleUrls: ['./user-read-books.component.css']
})
export class UserReadBooksComponent implements OnInit {

    private curBook: BookComponent = new BookComponent(this.apiService, this.route, this.router, this.storage);
    private curBookList: BooksListComponent = new BooksListComponent(this.apiService, this.route, this.router, this.storage);

    constructor(private apiService: CommonService, private route: ActivatedRoute, private router: Router,
                private storage: StorageService) {
    }

    ngOnInit() {
        this.curBookList.getAllReadBooks();
    }

}
