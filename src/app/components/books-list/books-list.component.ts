import { Component, OnInit } from '@angular/core';
import {CommonService} from '../../services/common/common.service';
import {Book} from '../../models/book'
import { ActivatedRoute } from '@angular/router';
import {BookFilter} from '../../models/bookfilter';
import {error} from "util";


export class SelectedItem{
  selected:boolean;
  name:string;
}

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})


export class BooksListComponent implements OnInit {

  books:Book[]=[];
  selectedBook:Book;
  createdBook:Book = new Book();
  bookFilter:BookFilter = new BookFilter();

  listOfAllAuthors:string[] =  [];
  listOfAllGenres:string[] =  [];

  selectedAuthors:SelectedItem[] = [{name:'author1', selected: false},{name:'author2', selected: false}];
  selectedGenres:SelectedItem[] = [{name:'genre1', selected: false},{name:'genre2', selected: false}];

  listOfSelectedGenres:SelectedItem[] = [];
  listOfSelectedAuthor:SelectedItem[] = [];



  constructor(private apiService: CommonService,private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.getBooks();

  // this.getAllAuthor();
  // this.getAllGenre();

  }

  getAllAuthor(){
    this.apiService.getAllAuthor().subscribe(
      res=>{
        this.listOfAllAuthors = res;
      },
      err=>{alert("error in get all author")}
    );

    for (var i=0; i<this.listOfAllAuthors.length;i++)
    {
      this.selectedAuthors.push({name:this.listOfAllAuthors[i], selected:false});
    }
  }
  getAllGenre(){
    this.apiService.getAllGenre().subscribe(
      res=>{
        this.listOfAllGenres = res;
      },
      err=>{alert("error in get all genre")}
    );
    for (var i=0; i<this.listOfAllGenres.length;i++)
    {
      this.selectedGenres.push({name:this.listOfAllGenres[i], selected:false});
    }
  }

  searchByFilter(){
    this.bookFilter.author = [];
    this.bookFilter.genre = [];

    this.listOfSelectedGenres= this.selectedGenres
      .filter(v => v.selected != false);
   this.listOfSelectedAuthor= this.selectedAuthors
      .filter(v => v.selected != false);


    this.listOfSelectedGenres.forEach(genre=>this.bookFilter.genre.push(genre.name));
    this.listOfSelectedAuthor.forEach(author=>this.bookFilter.author.push(author.name));

    this.apiService.getBooksByFilter(this.bookFilter).subscribe(
      res=>{this.books = res},
      error=>alert("error in filter")
    );

  }

  resetFiler(){
   this.bookFilter.header = "";
   this.bookFilter.author = [];
   this.bookFilter.genre = [];

   this.selectedGenres.forEach(genre=>genre.selected = false);
   this.selectedAuthors.forEach(author=>author.selected = false);

   this.getBooks();
  }

  getBooks():void{
    this.apiService.getBooks().subscribe(
      res=>{
        this.books = res;
      },
      err=>{alert("Error in get all reviews")}
    );
  }



  createBook():void{
    const newCreatedBook:Book = Object.assign({},this.createdBook);
    this.apiService.createBook(newCreatedBook)
      .subscribe(res=>{this.books.push(this.createdBook)},
          err=>{alert("Error in create book");
      });
  }
  getBook(book:Book): void {
    this.selectedBook = book;

    //this.apiService.getBookByName(selectedBook.title)
     // .subscribe(book => this.selectedBook = selectedBook);
  }

  searchByTitle(){
    if(this.bookFilter.header!=""){
      this.apiService.getBooksByTitle(this.bookFilter.header).subscribe(
        res=>{this.books = res},
        err =>{alert("error in get books by title");}
      );
    }
  }


  /*
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

 */


}






