import {Component, OnInit} from '@angular/core';
import {CommonService} from '../../services/common/common.service';
import {Book} from '../../models/book'
import {ActivatedRoute, Router} from '@angular/router';
import {BookFilter} from '../../models/bookfilter';
import {SelectedItem} from '../../models/selected-item-filter';

@Component({
    selector: 'app-books-list',
    templateUrl: './books-list.component.html',
    styleUrls: ['./books-list.component.css']
})

export class BooksListComponent implements OnInit {

    books: Book[] = [];
    createdBook: Book = new Book();
    bookFilter: BookFilter = new BookFilter();
    addBookVisible: boolean;
    listOfAllAuthors: string[] = [];
    listOfAllGenres: string[] = [];
    selectedAuthors: SelectedItem[] = [{id: 0, name: 'author1', selected: false}, {
        id: 1,
        name: 'author2',
        selected: false
    }];
    selectedGenres: SelectedItem[] = [{id: 0, name: 'genre1', selected: false}, {
        id: 1,
        name: 'genre2',
        selected: false
    }];
    listOfSelectedGenres: SelectedItem[] = [];
    listOfSelectedAuthor: SelectedItem[] = [];

    constructor(private apiService: CommonService, private route: ActivatedRoute, private router: Router) {
    }

    ngOnInit() {
        this.addBookVisible = false;
        this.getBooks();
        //this.getAllAuthor();
        //this.getAllGenre();
    }

    getAllAuthor() {
        this.apiService.getAllAuthor().subscribe(
            res => {
                this.listOfAllAuthors = res;
            },
            err => {
                alert("error in get all author")
            }
        );
        this.listOfAllAuthors.forEach(author => {
            this.selectedAuthors.push({id: 0, name: author, selected: false})
        });
    }

    getAllGenre() {
        this.apiService.getAllGenre().subscribe(
            res => {
                this.listOfAllGenres = res;
            },
            err => {
                alert("error in get all genre")
            }
        );
        this.listOfAllGenres.forEach(genre => {
            this.selectedGenres.push({id: 0, name: genre, selected: false})
        });
    }

    searchByFilter() {
        this.bookFilter.author = [];
        this.bookFilter.genre = [];

        this.listOfSelectedGenres = this.selectedGenres
            .filter(v => v.selected != false);
        this.listOfSelectedAuthor = this.selectedAuthors
            .filter(v => v.selected != false);

        this.listOfSelectedGenres.forEach(genre => this.bookFilter.genre.push(genre.name));
        this.listOfSelectedAuthor.forEach(author => this.bookFilter.author.push(author.name));

        this.apiService.getBooksByFilter(this.bookFilter).subscribe(
            res => {
                this.books = res
            },
            error => alert("error in filter")
        );

    }

    resetFiler() {
        this.bookFilter.header = "";
        this.bookFilter.author = [];
        this.bookFilter.genre = [];
        this.selectedGenres.forEach(genre => genre.selected = false);
        this.selectedAuthors.forEach(author => author.selected = false);
        this.getBooks();
    }

    getBooks(): void {
        this.apiService.getBooks().subscribe(
            res => {
                this.books = res;
                console.log(this.books);
            },
            err => {
                alert("Error in get all reviews")
            }
        );
    }

    createBook(): void {
        const newCreatedBook: Book = Object.assign({}, this.createdBook);
        this.apiService.createBook(newCreatedBook)
            .subscribe(res => {
                    this.books.push(this.createdBook)
                },
                err => {
                    alert("Error in create book");
                });
    }
    createBookFromChange(){

    }

}






