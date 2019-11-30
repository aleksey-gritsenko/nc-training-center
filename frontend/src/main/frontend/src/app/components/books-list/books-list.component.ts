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
    userId: any;

    userAddedBook: boolean = true;
    userAddedToRead: boolean = true;
    userAddedToFav: boolean = true;

    userBook:UserBook = new UserBook();

    userBooks : UserBook[] = [];

    constructor(private apiService: CommonService, private route: ActivatedRoute, private router: Router,
    private storage: StorageService) {

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
    searchByTitle(){
        this.apiService.getBooksByTitle(this.searchTitle).subscribe(
            res=>{
                this.books = res;
                this.books.forEach(book=>{
                    this.apiService.getAuthorsByBookId(book.id).subscribe(
                        authors => book.authors = authors
                    );
                    this.apiService.getGenreByBookId(book.id).subscribe(
                        genre=> book.genre  = genre.name
                    )
                })

            }
        )
    }
    searchByFilter() {
        this.bookFilter.author = [];
        this.bookFilter.genre = [];

        this.selectedGenres = this.selectedGenres
            .filter(v => v.selected);
        this.selectedAuthors = this.selectedAuthors
            .filter(v => v.selected);

        this.selectedGenres.forEach(genre => this.bookFilter.genre.push(genre.name));
        this.selectedAuthors.forEach(author => this.bookFilter.author.push(author.name));
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
                })

            },
            err => {
                alert("Error in get all reviews")
            }
        );
    }

    addBookToUser(bookId:number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }
        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;
        this.userAddedBook = true;

        this.apiService.addBookToUser(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Add  book error");
            }
        );
    }

    addBookToFavourite(bookId: number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }

        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToFav = false;

        this.apiService.markUserBookAsFavourite(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Add to FAV book error");
            }
        );
    }

    addBookToRead(bookId: number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }

        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToRead = false;

        this.apiService.markUserBookAsRead(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Add to READ book error");
            }
        );
    }

    removeBookFromFav(bookId: number){
       if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }

        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToFav = true;

        this.apiService.removeFromFavourite(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Remove from FAV book error");
            }
        );
    }

    removeBookFromRead(bookId: number){
        if (this.storage.getUser() == null) {
            this.router.navigate(['/login']);
        }

        let userBook:UserBook = new UserBook();
        userBook.userId = this.storage.getUser().id;
        userBook.bookId = bookId;

        this.userAddedToRead = true;

        this.apiService.removeFromRead(userBook).subscribe(
            res=>{
                console.log(res);
            },
            err=>{
                console.log("Remove from READ book error");
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
        return "abcdefghijklmnopqrstuvwxyz".split("");
    }

    filterGenre(char:string){
        this.filterGenres = this.selectedGenres.filter(genre=>genre.name.charAt(0).toLowerCase()==char);
        console.log(this.filterGenres);
    }
    filterAuthor(char:string){
        this.filterAuthors = this.selectedAuthors.filter(author=>author.name.charAt(0).toLowerCase()==char);
        console.log(this.filterAuthors);
    }
}






