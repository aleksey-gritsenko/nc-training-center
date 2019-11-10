import { Pipe, PipeTransform } from '@angular/core';
import {Book} from "../../models/book";

@Pipe({
  name: 'bookSearchTitle'
})
export class BookSearchTitlePipe implements PipeTransform {

  transform(books:Book[], text:string): Book[] {
    if(text == null || text ==="")
      return books;
    return books.filter(n=>n.title.includes(text));
  }

}
