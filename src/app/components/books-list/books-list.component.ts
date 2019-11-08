import { Component, OnInit } from '@angular/core';
import {CommonService} from '../../services/common/common.service';
import {Book} from '../../models/book'

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  books:Book[];
  constructor(private apiService: CommonService) { }

  model:Book = {
    title:'',
    header:'',
    author:'',
    overview: '',
    photo: 0,
    file: '',
    status:0
  };

  ngOnInit() {
   // this.getBooks();
  }


  getBooks():void{
    this.apiService.getBooks().subscribe(books => this.books = books);
  }

  createBook(){
    this.apiService.createBook(this.model);
  }



}
