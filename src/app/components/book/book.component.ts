import { Component, OnInit } from '@angular/core';
import {Book} from '../../models/book';
import{ CommonService } from "../../services/common/common.service";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  book:Book;
  constructor(private apiService:CommonService) { }

  ngOnInit() {
  }

  getBook(){

  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.apiService.updateBook(this.book)
      .subscribe(() => this.goBack());
  }
}
