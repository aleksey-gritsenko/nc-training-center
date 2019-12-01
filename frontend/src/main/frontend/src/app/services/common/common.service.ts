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

    private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';
    private localhost: string = 'http://localhost:8080/userBook';

    private booksUrl: string = `${this.siteUrl}/book`;
    private announcementsUrl: string = `${this.siteUrl}/announcements`;
    private reviewsUrl: string = `${this.siteUrl}/review`;
    private userBookUrl: string = `${this.siteUrl}/userBook`;

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
       const url = `${this.booksUrl}\\filter`;
       // const url = `http://localhost:8080/book/filter`;
        return this.http.post<Book[]>(url,filter);
    }

    getBooksByTitle(title:string):Observable<Book[]>{
        //TODO get books by title
        let params = new HttpParams()
            .set('title', title);
        const url = `${this.booksUrl}\\title`;
        return this.http.get<Book[]>(url, {params:params});
    }

    getBookById(id: number): Observable<Book> {
        const url = `${this.booksUrl}/id?id=${id}`;
        return this.http.get<Book>(url);
    }

    createBook(book: Book): Observable<Book> {
        console.log(book);
        return this.http.post<Book>(this.booksUrl, book);
    }


    updateBook(book: Book): Observable<Book> {
        const url = `${this.booksUrl}\\update`;
        return this.http.post<Book>(url, book);
    }

    getAnnouncements(): Observable<Announcement[]> {
        return this.http.get<Announcement[]>(this.announcementsUrl);
    }

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
        const url = `http://localhost:8080/review`;
       //const url =`${this.reviewsUrl}`;
        return this.http.post<Review>(url, review);
    }

    getAcceptedReviews(bookId: number, status: boolean):Observable<Review[]>{
        const params = new HttpParams()
            .set('book', bookId.toString())
            .set('status', JSON.stringify(status));
        const url =`${this.reviewsUrl}/accepted`;
        console.log(params);
        return this.http.get<Review[]>(url, {params:params});
    }

    acceptReview(review:Review):Observable<Review>{
        //const url =`${this.reviewsUrl}/accept`;
        const url = `http://localhost:8080/review/accept`;
        return this.http.post<Review>(url,review);
    }

    deleteReviewById(review:Review):Observable<Review>{
        const params = new HttpParams()
            .set('review',review.id.toString());
        const url = `${this.reviewsUrl}/delete`;
        console.log(params);
        return this.http.post<Review>(url,params);
    }
    getReviewById(reviewId:number){
        const url = `${this.reviewsUrl}/detail`;
        const params = new HttpParams()
            .set('review',reviewId.toString());
        return this.http.get<Review>(url,{params:params});
    }


    getAnnouncementsByFilter(): Observable<Announcement[]> {
        return null;
    }

    addBookToUser(userBook:UserBook):Observable<UserBook>{
        const url = `${this.localhost}/add`;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    getAllUserBooks(userBook: UserBook):Observable<Book[]>{
        const url = `${this.userBookUrl}/all`;
        const params = new HttpParams()
            .set('userId', userBook.userId.toString());
        return this.http.get<Book[]>(url, {params:params});
    }

    markUserBookAsRead(userBook: UserBook): Observable<UserBook>{
        const url = `${this.userBookUrl}/mark_read`;
        userBook.isRead = true;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    markUserBookAsFavourite(userBook: UserBook): Observable<UserBook>{
        const url = `${this.userBookUrl}/mark_fav`;
        userBook.isFavorite = true;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    getAllFavouriteBooks(userBook: UserBook): Observable<UserBook[]>{
        const params = new HttpParams()
            .set('userId',userBook.userId.toString());
        const url = `${this.userBookUrl}/all/favourite`;
        return this.http.get<UserBook[]>(url, {params:params});
    }

    getAllReadBooks(userBook: UserBook): Observable<UserBook[]>{
        const params = new HttpParams()
            .set('userId',userBook.userId.toString());
        const url = `${this.userBookUrl}/all/read`;
        return this.http.get<UserBook[]>(url, {params:params});
    }

    removeFromFavourite(userBook: UserBook): Observable<UserBook>{
        const url = `${this.userBookUrl}/remove_fav`;
        userBook.isFavorite = false;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    removeFromRead(userBook: UserBook): Observable<UserBook>{
        const url = `${this.userBookUrl}/remove_read`;
        userBook.isRead = false;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    deleteFromAdded(userBook: UserBook): Observable<UserBook>{
        const url = `${this.userBookUrl}/delete`;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    makeSuggestion(userId: number):Observable<Book[]>{
        const url = `http://localhost:8080//book/suggestion?user=${userId}`;
        ///const url = `${this.booksUrl}/suggestion?user=${userId}`;
        return this.http.get<Book[]>(url);

    }
    getMostRatedBooks():Observable<Book[]>{
        const url = `http://localhost:8080//book/rate`;
        ///const url = `${this.booksUrl}/rate`;
        return this.http.get<Book[]>(url);
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
