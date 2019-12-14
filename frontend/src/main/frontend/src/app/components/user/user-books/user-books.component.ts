import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../../services/common/common.service";
import {BooksListComponent} from "../../books-list/books-list.component";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {BookFilter} from "../../../models/bookfilter";

@Component({
    selector: 'app-user-books',
    templateUrl: './user-books.component.html',
    styleUrls: ['./user-books.component.css']
})
export class UserBooksComponent extends BooksListComponent implements OnInit {

    constructor(apiService: CommonService,
                route: ActivatedRoute,
                router: Router,
                storage: StorageService) {
        super(apiService, route, router, storage);
    }

    ngOnInit() {
        super.getUsersBookList();

        super.getAllAuthor();
        super.getAllGenre();
        super.checkHistoryFilter();
    }
}
