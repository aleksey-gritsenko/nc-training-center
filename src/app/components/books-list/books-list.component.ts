import { Component, OnInit } from '@angular/core';
import {CommonService} from '../../services/common/common.service';
import {Book} from '../../models/book'
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  books:Book[]=[];
  selectedBook:Book;
  searchBook:string;

  model:Book = {
    id: null,
    title:'',
    header:'',
    author:'',
    overview:'',
    photo:0,
    file:0,
    status:''
  };
  constructor(private apiService: CommonService,private route: ActivatedRoute) { }

  ngOnInit() {
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
