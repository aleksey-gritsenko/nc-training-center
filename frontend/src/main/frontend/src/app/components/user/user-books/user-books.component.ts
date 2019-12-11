import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../../services/common/common.service";
import {BooksListComponent} from "../../books-list/books-list.component";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-user-books',
    templateUrl: './user-books.component.html',
    styleUrls: ['./user-books.component.css']
})
export class UserBooksComponent extends BooksListComponent implements OnInit {

    private store: StorageService;

    constructor(apiService: CommonService,
                route: ActivatedRoute,
                router: Router,
                storage: StorageService) {
        super(apiService, route, router, storage);
    }

    ngOnInit() {
        super.getUsersBookList();
        this.getAllGenre();
        this.getAllAuthor();
        super.getBooks();
        this.historyFilter = this.store.getFilter();
    }
}
