import { Component, OnInit, Input } from '@angular/core';
import {Book} from '../../models/book';
import{ CommonService } from "../../services/common/common.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  @Input() book:Book;

  constructor(private apiService:CommonService,private route: ActivatedRoute) { }

  ngOnInit() {
    this.getBook();
  }
  updateBook():void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.book.id  = id;
    const newCreatedBook:Book = Object.assign({},this.book);
    this.apiService.updateBook(newCreatedBook);
    console.log(newCreatedBook);
}
  getBook():void{
    const id = +this.route.snapshot.paramMap.get('id');
    this.apiService.getBookById(id).subscribe(book=>this.book=book);
  }

}
