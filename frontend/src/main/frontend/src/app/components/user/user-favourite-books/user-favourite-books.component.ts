import {Component, OnInit} from '@angular/core';
import {BooksListComponent} from "../../books-list/books-list.component";
import {CommonService} from "../../../services/common/common.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-user-favourite-books',
    templateUrl: './user-favourite-books.component.html',
    styleUrls: ['./user-favourite-books.component.css']
})
export class UserFavouriteBooksComponent extends BooksListComponent implements OnInit {

    private store: StorageService;

    constructor(apiService: CommonService,
                route: ActivatedRoute,
                router: Router,
                storage: StorageService) {
        super(apiService, route, router, storage);
    }

    ngOnInit() {
        super.getAllFavouriteBooks();

        super.getAllAuthor();
        super.getAllGenre();
        this.historyFilter = this.store.getFilter();
    }
}
