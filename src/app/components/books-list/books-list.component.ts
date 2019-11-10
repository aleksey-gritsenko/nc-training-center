import { Component, OnInit } from '@angular/core';
import {CommonService} from '../../services/common/common.service';
import {Book} from '../../models/book'
import { ActivatedRoute } from '@angular/router';
import {BookFilter} from '../../models/bookfilter';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  books:Book[]=[];
  selectedBook:Book;
  searchBook:string;
  bookFilter:BookFilter = new BookFilter();

  allAuthorList:string[] = [];
  allGenreList:string[] = [];

  model:Book = {
    title:'',
    header:'',
    author:'',
    overview:'',
    photo:0,
    file:0,
    status:'',
    id:0
  };
  constructor(private apiService: CommonService,private route: ActivatedRoute) { }

  ngOnInit() {
  //this.getBooks();
  // this.getAllAuthor();
  // this.getAllGenre();
  }

  getAllAuthor(){
    this.apiService.getAllAuthor().subscribe(
      res=>{
        this.allAuthorList = res;
      },
      err=>{alert("error in get all author")}
    );
  }

  getAllGenre(){
    this.apiService.getAllGenre().subscribe(
      res=>{
        this.allGenreList = res;
      },
      err=>{alert("error in get all genre")}
    );
  }


  addGenreToFilter(genre:string) {
    this.bookFilter.genre = genre;
  }

  addAuthorToFilter(author:string){
    this.bookFilter.author = author;
  }

  searchByFilter(){
    if(this.bookFilter!=null){
      console.log(this.bookFilter);
      this.apiService.getBooksByFilter(this.bookFilter);
    }
  }

  resetFiler(){
    this.bookFilter.author = "";
    this.bookFilter.genre = "";
    //TODO: reset filter in DB
  }


  getBooks():void{
    this.apiService.getBooks().subscribe(
      res=>{
        this.books = res;
      },
      err=>{alert("Error in get all reviews")}
    );
  }
  createBook() {
    //delete this part start
   this.books.push(this.model);
    //delete  this part end
    this.apiService.createBook(this.model).subscribe(res=>{this.books.push(this.model)},err=>{alert("Error in create book");});
  }
  getBook(book:Book): void {
    this.selectedBook = book;

    //this.apiService.getBookByName(selectedBook.title)
     // .subscribe(book => this.selectedBook = selectedBook);
  }
  deleteBook(book:Book) {
   //delete this part start
    let indexOfBook = this.books.indexOf(book);
    this.books.splice(indexOfBook, 1);
    //delete  this part end

    if (confirm("Are you sure you want to delete book?")) {
      this.apiService.deleteBook(book.id).subscribe(
        res => {
          let indexOfBook = this.books.indexOf(book);
          this.books.splice(indexOfBook, 1);
        },
        err => {
          alert("Could not delete book");
        }
      );
    }
  }
}
