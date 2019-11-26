import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from "../../models/book";
import {BookFilter} from "../../models/bookfilter";
import {Announcement} from "../../models/announcement";
import {Review} from "../../models/review";
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";
import {UserBook} from "../../models/userBook";

@Injectable({
    providedIn: 'root'
})
export class CommonService {

    private booksUrl = 'http://localhost:8080/book'; // change with necessity
    private announcementsUrl = '/announcements'; // change with necessity
    private reviewsUrl = 'http://localhost:8080/review';
    // private reviewsUrl = 'api/books'; // -?

    // announcements : Announcement[] = [];
    // reviews : Review[] = [];

    constructor(private http: HttpClient) {
    }

    httpOptions = {
        headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8')
            .set('Accept', 'application/json').set('Access-Control-Allow-Origin', '*')
    };

    getBooks(): Observable<Book[]> {
        const url = `${this.booksUrl}\\all`;
        return this.http.get<Book[]>(url);
    }

    getBooksByFilter(filter: BookFilter): Observable<Book[]> {

        let params = new HttpParams()
            .set('header', filter.header)
            .set('author', JSON.stringify(filter.author))
            .set('genre',  JSON.stringify(filter.genre));

        const url = `${this.booksUrl}\\filter`;
        console.log(params);
        return this.http.get<Book[]>(url, {params: params});
    }


    getBookById(id: number): Observable<Book> {
        const url = `${this.booksUrl}/id?id=${id}`;
        return this.http.get<Book>(url);
    }

    createBook(book: Book): Observable<Book> {
        return this.http.post<Book>(this.booksUrl, book);
    }


    updateBook(book: Book): Observable<Book> {
        const url = `${this.booksUrl}\\update`;
        return this.http.post<Book>(url, book);
    }

    getAnnouncements(): Observable<Announcement[]> {
        return this.http.get<Announcement[]>(this.announcementsUrl);
    }

    // getAnnouncementsByFilter() : Observable<Announcement[]>{
    // }

    getAuthorsByBookId(bookId: number): Observable<Author[]>{
        const url = `${this.booksUrl}\\authors\\book`;
        const params = new HttpParams()
            .set("book", bookId.toString());
        return this.http.get<Author[]>(url,{params:params});
    }

    getGenreByBookId(bookId: number): Observable<Genre>{
        const url = `${this.booksUrl}\\genre\\book`;
        const params = new HttpParams()
            .set("book", bookId.toString());
        return this.http.get<Genre>(url,{params:params});
    }

    getAllAuthor(): Observable<Author[]> {
        const url = `${this.booksUrl}/authors`;
        return this.http.get<Author[]>(url);
    }

    getAllGenre(): Observable<Genre[]> {
        const url = `${this.booksUrl}/genres`;
        return this.http.get<Genre[]>(url);
    }

    getReviews(id: number): Observable<Review[]> {
        let body = new HttpParams()
            .set('book', id.toString());
        const url =`${this.reviewsUrl}/all`;
        return this.http.get<Review[]>(url, {params:body});
    }

    createReview(review: Review): Observable<Review> {
        console.log(review);
        const body = new HttpParams()
            .set('book', review.bookId.toString())
            .set('user', review.userId.toString())
            .set('text', review.text)
            .set('grade', review.grade.toString());
        console.log(body);

        return this.http.post<Review>(this.reviewsUrl, body, this.httpOptions);
    }

    getAcceptedReviews(bookId: number, status: boolean):Observable<Review[]>{
        const body = new HttpParams()
            .set('book', bookId.toString())
            .set('status', JSON.stringify(status));
        const url =`${this.reviewsUrl}/accepted`;
        console.log(body);
        return this.http.get<Review[]>(url, {params:body});
    }

    acceptReview(review:Review, status:boolean):Observable<Review>{
        const body = new HttpParams()
            .set('review', review.id.toString())
            .set('status', JSON.stringify(status))
            .set('admin', review.adminId.toString());
        const url =`${this.reviewsUrl}/accept`;
        console.log(body);
        return this.http.post<Review>(url,body);
    }

    deleteReviewById(review:Review):Observable<Review>{
        const body = new HttpParams()
            .set('review',review.id.toString());
        const url = `${this.reviewsUrl}/delete`;
        console.log(body);
        return this.http.post<Review>(url,body);
    }
    getAnnouncementsByFilter(): Observable<Announcement[]> {
        return null;
    }

    addBookToUser(userBook:UserBook):Observable<UserBook>{
        const url = `http://localhost:8080/userbook`;
        return this.http.post<UserBook>(url, userBook);
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
