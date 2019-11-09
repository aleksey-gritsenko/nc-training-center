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
  book:Book = new Book();
  selectedBook:Book;

  model:Book = {
    title:'',
    header:'',
    author:'',
    overview:'',
    photo:0,
    file:0,
    status:''
  };
  constructor(private apiService: CommonService,private route: ActivatedRoute,) { }

  ngOnInit() {
   this.getBooks();
  }
  getBooks():void{
    this.apiService.getBooks().subscribe(books => this.books = books);
  }
  createBook() {
    this.apiService.createBook(this.book);
    window.location.reload();
  }
  getBook(book:Book): void {
    this.apiService.getBookByName(book.title)
      .subscribe(book => this.book = book);
  }

  deleteBook(book:Book){
    this.apiService.deleteBook(book.title).subscribe();
  }

}
