import {Component, OnInit} from '@angular/core';
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../services/storage/storage.service";
import {BooksListComponent} from "../books-list/books-list.component";
import {BookComponent} from "../book/book.component";

@Component({
    selector: 'app-user-books',
    templateUrl: './user-books.component.html',
    styleUrls: ['./user-books.component.css']
})
export class UserBooksComponent implements OnInit {

    constructor(private apiService: CommonService, private route: ActivatedRoute, private router: Router,
                private storage: StorageService, private bookList: BooksListComponent,
                private book: BookComponent) {
    }

    ngOnInit() {
    }

}
