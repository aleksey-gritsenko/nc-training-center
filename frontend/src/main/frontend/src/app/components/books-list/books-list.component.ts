import {Component, OnInit} from '@angular/core';
import {CommonService} from '../../services/common/common.service';
import {Book} from '../../models/book'
import {ActivatedRoute, Router} from '@angular/router';
import {BookFilter} from '../../models/bookfilter';
import {SelectedItem} from '../../models/selected-item-filter';
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";
import {UserBook} from "../../models/userBook";
import {StorageService} from "../../services/storage/storage.service";



@Component({
    selector: 'app-books-list',
    templateUrl: './books-list.component.html',
    styleUrls: ['./books-list.component.css']
})

export class BooksListComponent implements OnInit{
    createdAuthors: string;
    searchTitle:string;
    genres: Genre[] = [];
    authors: Author[] = [];
    books: Book[] = [];

    userBooks : UserBook[] = [];
    userBookList: Book[] = [];
    book: Book;

    model : Book = {
        id :0,
        header: '',
        overview: '',
        photoId: 0,
        fileId: 0,
        status: '',
        genre: '',
        authors: []
    };

    bookFilter: BookFilter = new BookFilter();
    addBookVisible: boolean;
    selectedAuthors: SelectedItem[] =[];
    selectedGenres: SelectedItem[] = [];
    filterGenres:SelectedItem[]=[];
    filterAuthors:SelectedItem[]=[];
    historyFilter:BookFilter;

    userId: any;

    constructor(private apiService: CommonService, private route: ActivatedRoute, private router: Router,
                private storage: StorageService) {

    }

    ngOnInit() {
        this.getBooks();
        this.addBookVisible = false;
        this.getAllAuthor();
        this.getAllGenre();
        this.historyFilter = this.storage.getFilter();
        console.log(this.historyFilter);
    }

    getUsersBookList(){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }
        let userBook: UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        //userBook.bookId = this.book.id;
        this.apiService.getAllUserBooks(userBook).subscribe(
            res => {
                console.log(userBook);
                console.log(res);
                this.userBookList = res;
            },
            err => {
                console.log(userBook);
                console.log(this.userBookList);
                console.log("Error in getting all users books")
            }
        );
    }


    getAllAuthor() {
        this.apiService.getAllAuthor().subscribe(
            res => {
                this.authors = res;
                this.authors.forEach(author => {
                    this.selectedAuthors.push({name: author.name, selected: false});
                });
                console.log(this.authors);
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
                console.log(this.genres);
            },
            err => {
                alert("error in get all genre")
            }
        );

    }
    searchByTitle(){
        if(this.searchTitle!="") {
            this.bookFilter.header = this.searchTitle;
            this.apiService.getBooksByFilter(this.bookFilter).subscribe(
                res => {
                    this.books = res;
                    this.books.forEach(book => {
                        this.apiService.getAuthorsByBookId(book.id).subscribe(
                            authors => book.authors = authors
                        );
                        this.apiService.getGenreByBookId(book.id).subscribe(
                            genre => book.genre = genre.name
                        )
                    })

                }
            )
        }
    }
    searchByFilter() {
        this.bookFilter.author = [];
        this.bookFilter.genre = [];

        this.filterGenres = this.selectedGenres
            .filter(v => v.selected);
        this.filterAuthors = this.selectedAuthors
            .filter(v => v.selected);

        this.filterGenres.forEach(genre => {
            this.bookFilter.genre.push(genre.name);
        });
        this.filterAuthors.forEach(author => {
            this.bookFilter.author.push(author.name);
        });

        this.historyFilter.genre.push(...(this.bookFilter.genre||[]));
        this.historyFilter.author.push(...(this.bookFilter.author||[]));
        this.storage.setFilter(this.historyFilter);

        this.books = [];
        this.apiService.getBooksByFilter(this.bookFilter).subscribe(
            res => {
                this.books = res;
                this.books.forEach(book=>{
                    this.apiService.getAuthorsByBookId(book.id).subscribe(
                        authors => book.authors = authors
                    );
                    this.apiService.getGenreByBookId(book.id).subscribe(
                        genre=> book.genre  = genre.name
                    )
                })
            },
            error => alert("error in filter")
        );

    }

    resetFiler() {
        this.bookFilter.header = "";
        this.bookFilter.author = [];
        this.bookFilter.genre = [];
        this.filterGenres = [];
        this.filterAuthors = [];
        this.selectedGenres.forEach(genre => genre.selected = false);
        this.selectedAuthors.forEach(author => author.selected = false);
        this.getBooks();
    }

    getBooks(): void {
        this.apiService.getBooks().subscribe(
            res => {
                this.books = res;
                this.books.forEach(book=>{
                    this.apiService.getAuthorsByBookId(book.id).subscribe(
                        authors => book.authors = authors
                    );
                    this.apiService.getGenreByBookId(book.id).subscribe(
                        genre=> book.genre  = genre.name
                    )
                });

            },
            err => {
                alert("Error in get all reviews")
            }
        );
    }

    createBook(): void {
        this.createdAuthors.split(',').forEach(name=>{
            let author = new Author();
            author.name = name;
            this.model.authors.push(author);
        });
        const newCreatedBook: Book = Object.assign({}, this.model);
        newCreatedBook.authors = this.model.authors;
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



    fillArray():string[]{
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    }

    filterGenre(char:string){
        this.filterGenres = this.selectedGenres.filter(genre=>genre.name.charAt(0).toUpperCase()==char);
    }
    filterAuthor(char:string){
        this.filterAuthors = this.selectedAuthors.filter(author=>author.name.charAt(0).toUpperCase()==char);
    }
}






