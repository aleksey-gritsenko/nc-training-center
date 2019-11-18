import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse,HttpParams} from '@angular/common/http';
import { Observable,of,throwError  } from 'rxjs';
import { Book } from 'src/app/models/book';
import { Announcement } from 'src/app/models/announcement';
import { Review } from 'src/app/models/review';
import { /*Filter,*/BookFilter } from 'src/app/models/bookfilter';
import {arrayify} from "tslint/lib/utils";

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  private booksUrl = 'http://localhost:8080/book'; // change with necessity
  private announcementsUrl = '/announcements'; // change with necessity

  // private reviewsUrl = 'api/books'; // -?

  // announcements : Announcement[] = [];
  // reviews : Review[] = [];

  constructor(private http: HttpClient) {
  }
  httpOptions = {headers:new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8')
        .set('Accept', 'application/json').set('Access-Control-Allow-Origin','*')};


  getBooks(): Observable<Book[]> {
    const url = `${this.booksUrl}\\all`;
    return this.http.get<Book[]>(url);
    //return null;
  }

  getBooksByFilter(filter: BookFilter): Observable<Book[]> {
    let body = {
      'header':filter.header,
      'author':filter.author,
      'genre':filter.genre
    };
    const url = `${this.booksUrl}\\filter`;
    return this.http.get<Book[]>(url,{ params:body});
  }

  getBooksByTitle(title:string):Observable<Book[]>{
    const url = `${this.booksUrl}\\title`;
    let params = new HttpParams()
        .set('title' , title);

    return  this.http.get<Book[]>(url, {params:params});
  }

  getBookByName(name: string): Observable<Book>{
    return null;
  }

  getBookById(id:number){
    const url = `${this.booksUrl}/?id=${id}`;
    return this.http.get<Book>(url);
  }

  createBook(book:Book):Observable<Book>{
    const body = new HttpParams()
        .set('header', book.header)
        .set('author', book.author)
        .set('status', book.status)
        .set('overview', book.overview)
        .set('photo', book.photo.toString())
        .set('file', book.photo.toString());
    console.log(body);

    return this.http.post<Book>(this.booksUrl, body, this.httpOptions);
  }

  deleteBook(id:number):Observable<Book>{
    const url = `${this.booksUrl}/?id=${id}`;
    return this.http.delete<Book>(url);
  }

  updateBook(book:Book){
    const body = new HttpParams()
        .set('bookId',book.id.toString())
        .set('header', book.header)
        .set('author', book.author)
        .set('status', book.status)
        .set('overview', book.overview)
        .set('photo', book.photo.toString());

    console.log(body);
    const url = `${this.booksUrl}\\update\\?id=${book.id}`;
    this.http.post(this.booksUrl, body, this.httpOptions);
  }
  getAnnouncements(): Observable<Announcement[]> {
    return this.http.get<Announcement[]>(this.announcementsUrl);
  }
  /*getAnnouncementsByFilter() : Observable<Announcement[]>{
  }
*/
  getAllAuthor():Observable<string[]>{
    const url = `${this.booksUrl}/author`;
    //return this.http.get<string[]>(url);
    return null;
  }
  getAllGenre():Observable<string[]>{
    const url = `${this.booksUrl}/genre`;
    //return this.http.get<string[]>(url);
    return null;
  }

  getReviews(id:number) : Observable<Review[]>{
    const url = `${this.booksUrl}/${id}/review`;
    // return this.http.get<Review[]>(url);
    return null;
  }

  createReview(review:Review):Observable<Review>{
    const body = new HttpParams()
        .set('book', review.book.toString())
        .set('user', review.user.toString())
        .set('text', review.text)
        .set('reviewDate', review.reviewDate.toDateString())
        .set('grade', review.grade.toString())
        .set('adminId', review.adminId.toString());
    console.log(body);

    return this.http.post<Review>(this.booksUrl, body, this.httpOptions);
  }



  getAnnouncementsByFilter() : Observable<Announcement[]>{
    return null;
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
