import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from "../../models/book";
import {BookFilter} from "../../models/bookfilter";
import {Announcement} from "../../models/announcement";
import {Review} from "../../models/review";
import {Genre} from "../../models/genre";
import {Author} from "../../models/author";

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
        const body = new HttpParams()
            .set('header', book.header)
            .set('status', book.status)
            .set('overview', book.overview)
            .set('genre', book.genre)
            .set('authors', JSON.stringify(book.authors))
            .set('photo', book.photoId.toString())
            .set('file', book.fileId.toString());

        return this.http.post<Book>(this.booksUrl, body, this.httpOptions);
    }


    updateBook(book: Book): Observable<Book> {
        console.log(book.id);
        const body = new HttpParams()
            .set('bookId', book.id.toString())
            .set('header', book.header)
            .set('overview', book.overview)
            .set('photo', book.photoId.toString())
            .set('file', book.fileId.toString())
            .set('genre', book.genre)
            .set('authors', JSON.stringify(book.authors))
            .set('status', book.status);

        console.log(body);
        const url = `${this.booksUrl}\\update`;
        return this.http.post<Book>(this.booksUrl, body, this.httpOptions);
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
        const url = `${this.booksUrl}/${id}/review`;
        return this.http.get<Review[]>(url);
    }

    createReview(review: Review): Observable<Review> {
        const body = new HttpParams()
            .set('book', review.book.toString())
            .set('user', review.user.toString())
            .set('text', review.text)
            .set('reviewDate', review.reviewDate.toDateString())
            .set('grade', review.grade.toString())
            .set('status', review.status.toString())
            .set('adminId', review.adminId.toString());
        console.log(body);

        return this.http.post<Review>(this.booksUrl, body, this.httpOptions);
    }


    getAnnouncementsByFilter(): Observable<Announcement[]> {
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
