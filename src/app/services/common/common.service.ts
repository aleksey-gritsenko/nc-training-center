import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse} from '@angular/common/http';
import { Observable,of,throwError  } from 'rxjs';
import { Book } from 'src/app/models/book';
import { Announcement } from 'src/app/models/announcement';
import { Review } from 'src/app/models/review';
import { /*Filter,*/BookFilter } from 'src/app/models/bookfilter';

import { catchError, map, tap, retry} from 'rxjs/operators';
import set = Reflect.set;

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  private booksUrl = 'http://localhost:8080/book'; // change with necessity
  private announcementsUrl = '/announcements'; // change with necessity

  // private reviewsUrl = 'api/books'; // -?

  // books : Book[] = [];
  // announcements : Announcement[] = [];
  // reviews : Review[] = [];

  constructor(private http: HttpClient) {
  }

  httpOptions = {headers:new HttpHeaders().set('Content-Type', 'application/json')
      .set('Accept', 'application/json').set('Access-Control-Allow-Origin','*')};

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.booksUrl);
  }
  getBooksByFilter(filter: BookFilter): Observable<Book[]> { // + filter ?
    const url = `${this.booksUrl}/${filter.author}/${filter.genre}`;
    return this.http.get<Book[]>(url);
  }
  getBookByTitle(title:string){
    const url = `${this.booksUrl}/?title=${title}`;
    return this.http.get<Book>(url);
  }
  createBook(book:Book){
    console.log(book);
    this.http.post(this.booksUrl, book, this.httpOptions);
  }
  deleteBook(title:string){
    const url = `${this.booksUrl}/?title=${title}`;
    return this.http.post<Book>(this.booksUrl,title, this.httpOptions);
  }
  updateBook(book:Book){
    this.http.post(this.booksUrl, book, this.httpOptions);
  }

  getBookByName(name: string): Observable<Book> {
    const url = `${this.booksUrl}/${name}`;
    return this.http.get<Book>(url);
  }

  getAnnouncements(): Observable<Announcement[]> {
    return this.http.get<Announcement[]>(this.announcementsUrl);
  }

  getAnnouncementsByFilter() : Observable<Announcement[]>{
  }

  getReviews(book : Book) : Observable<Review[]>{

  }

  recoverPassword() {

  }

  changePassword() {

  }

  manageNotifications() {

  }

  login() {

  }
}
