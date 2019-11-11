import { Component, OnInit } from '@angular/core';
import {CommonService} from '../../services/common/common.service';
import {Book} from '../../models/book'
import { ActivatedRoute } from '@angular/router';
import {BookFilter} from '../../models/bookfilter';


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
  bookFilter:BookFilter = new BookFilter();

  listOfAllAuthors:string[] =  [];
  listOfAllGenres:string[] =  [];

  selectedAuthors:SelectedItem[] = [{name:'author1', selected: false},{name:'author2', selected: false}];
  selectedGenres:SelectedItem[] = [{name:'genre1', selected: false},{name:'genre2', selected: false}];


  listOfSelectedGenres:SelectedItem[] = [];
  listOfSelectedAuthor:SelectedItem[] = [];


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


  constructor(private apiService: CommonService,private route: ActivatedRoute) {


  }



  ngOnInit() {
  //this.getBooks();
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

   for (var i = 0; i<this.listOfSelectedAuthor.length;i++)
    {
      this.bookFilter.author.push(this.listOfSelectedAuthor[i].name);
    }
    for (var i = 0; i<this.listOfSelectedGenres.length;i++)
    {
      this.bookFilter.genre.push(this.listOfSelectedGenres[i].name);
    }

    console.log(this.bookFilter);
    this.apiService.getBooksByFilter(this.bookFilter);

  }

  resetFiler(){
   this.bookFilter.header = "";
   this.bookFilter.author = [];
   this.bookFilter.genre = [];

   for(var i = 0; i<this.selectedGenres.length;i++){
     this.selectedGenres[i].selected = false;
   }
    for(var i = 0; i<this.selectedAuthors.length;i++){
      this.selectedAuthors[i].selected = false;
    }


    //this.getBooks();

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






