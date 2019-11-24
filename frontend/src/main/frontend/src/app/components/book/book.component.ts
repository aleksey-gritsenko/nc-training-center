import {Component, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import {CommonService} from "../../services/common/common.service";
import {ActivatedRoute} from "@angular/router";
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";

@Component({
    selector: 'app-book',
    templateUrl: './book.component.html',
    styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
    book: Book = new Book();
    authors: Author[] = [];
    constructor(private apiService: CommonService, private route: ActivatedRoute) {
    }

    id: any;

    ngOnInit() {
        this.id = parseInt(this.route.snapshot.paramMap.get('id'));
        this.getBook();
    }

    updateBook(): void {
        const newCreatedBook: Book = Object.assign({}, this.book);
        this.book.authors = [];
        this.authors.forEach(author=>{this.book.authors.push(author.name)});
        console.log(newCreatedBook);
        this.apiService.updateBook(newCreatedBook).subscribe(
            res => {
                this.book = res;
                console.log(res);
            }
        );
    }

    getBook(): void {
        console.log(this.id);
        this.apiService.getBookById(this.id).subscribe(
            res => {
                this.book = res;
                this.apiService.getGenreByBookId(this.id).subscribe(
                    genre=>{
                        this.book.genre = genre.name;
                    });
                this.apiService.getAuthorsByBookId(this.id).subscribe(
                    authors=>{
                        this.book.authors = [];
                        this.authors = authors;
                        authors.forEach(author=>this.book.authors.push(author.name));
                    }
                );
            },
            err => {
                alert("Error in get book by id")
            }
        );
    }

}

