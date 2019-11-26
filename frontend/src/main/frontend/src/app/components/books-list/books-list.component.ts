import {Component, OnInit} from '@angular/core';
import {CommonService} from '../../services/common/common.service';
import {Book} from '../../models/book'
import {ActivatedRoute, Router} from '@angular/router';
import {BookFilter} from '../../models/bookfilter';
import {SelectedItem} from '../../models/selected-item-filter';
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";
import {UserBook} from "../../models/userBook";
import {error} from "util";

@Component({
    selector: 'app-books-list',
    templateUrl: './books-list.component.html',
    styleUrls: ['./books-list.component.css']
})

export class BooksListComponent implements OnInit{
    createdAuthors: string;
    genres: Genre[] = [];
    authors: Author[] = [];
    books: Book[] = [];
    createdBook: Book = new Book();
    bookFilter: BookFilter = new BookFilter();
    addBookVisible: boolean;
    selectedAuthors: SelectedItem[] =[];
    selectedGenres: SelectedItem[] = [];
    listOfSelectedGenres: SelectedItem[] = [];
    listOfSelectedAuthor: SelectedItem[] = [];
    userId: any;

    constructor(private apiService: CommonService, private route: ActivatedRoute, private router: Router) {

    }

    ngOnInit() {
        this.getBooks();
        this.addBookVisible = false;
        this.getAllAuthor();
        this.getAllGenre();
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
                alert("error in get all author")
            }
        );
    }

    getAllGenre() {
        this.apiService.getAllGenre().subscribe(
            res => {
                this.genres = res;
                this.genres.forEach(genre => {
                    this.selectedGenres.push({name: genre.name, selected: false})
                });
            },
            err => {
                alert("error in get all genre")
            }
        );

    }

    searchByFilter() {
        this.bookFilter.author = [];
        this.bookFilter.genre = [];

        this.listOfSelectedGenres = this.selectedGenres
            .filter(v => v.selected);
        this.listOfSelectedAuthor = this.selectedAuthors
            .filter(v => v.selected);

        this.listOfSelectedGenres.forEach(genre => this.bookFilter.genre.push(genre.name));
        this.listOfSelectedAuthor.forEach(author => this.bookFilter.author.push(author.name));

        this.apiService.getBooksByFilter(this.bookFilter).subscribe(
            res => {
                this.books = res;
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
                console.log(res);
                this.books.forEach(book=>{
                     this.apiService.getAuthorsByBookId(book.id).subscribe(
                        authors => book.authors = authors
                     );
                     this.apiService.getGenreByBookId(book.id).subscribe(
                         genre=> book.genre  = genre
                     )
                })

            },
            err => {
                alert("Error in get all reviews")
            }
        );
    }

    addBookToUser(bookId:number){
        this.userId = parseInt(this.route.snapshot.paramMap.get('id'));
        console.log(this.userId + " " + bookId);
        if(isNaN(this.userId))
        {
            this.router.navigate(['/login']);
        }
        let userBook:UserBook = new UserBook();
        userBook.bookId = bookId;
        userBook.userId = this.userId;
        this.apiService.addBookToUser(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Show error");
            }


        );
    }


    createBook(): void {
        this.createdAuthors.split(',').forEach(name=>{
            let author = new Author();
            author.name = name;
            this.createdBook.authors.push(author);
        });
        const newCreatedBook: Book = Object.assign({}, this.createdBook);
        this.apiService.createBook(newCreatedBook)
            .subscribe(res => {
                    this.books.push(res);
                    console.log(newCreatedBook);
                },
                err => {
                    alert("Error in create book");
                });

    }
    createBookFromChange(){

    }

}






