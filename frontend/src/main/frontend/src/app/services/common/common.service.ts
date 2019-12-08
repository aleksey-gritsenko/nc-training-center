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
import {User} from "../../models/user";

@Injectable({
    providedIn: 'root'
})
export class CommonService {

    private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';
    // if you want to test your code on localhost - change siteUrl to localhost where this is needed
    private localhost: string = 'http://localhost:8080';

    private booksUrl: string = `${this.siteUrl}/book`;
    private announcementsUrl: string = `${this.siteUrl}/announcements`;
    private reviewsUrl: string = `${this.siteUrl}/review`;
    private userBookUrl: string = `${this.siteUrl}/userBook`;
    private userFriendsUrl: string = `${this.siteUrl}/friends`;

    constructor(private http: HttpClient) {
    }

    httpOptions = {
        headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8')
            .set('Accept', 'application/json').set('Access-Control-Allow-Origin', '*')
    };

    getBooks(): Observable<Book[]> {
        //const url = `${this.booksUrl}/all`;
        const url = `${this.localhost}/book/all`;
        return this.http.get<Book[]>(url);
    }

    getBooksByFilter(filter: BookFilter): Observable<Book[]> {
        //const url = `${this.booksUrl}/filter`;
        const url = `${this.localhost}/book/filter`;
        console.log(filter);
        return this.http.post<Book[]>(url, filter);
    }

    getBooksByTitle(title: string): Observable<Book[]> {
        //TODO get books by title
        let params = new HttpParams()
            .set('title', title);
        //const url = `${this.booksUrl}/title`;
        const url = `${this.localhost}/book/title`;
        return this.http.get<Book[]>(url, {params: params});
    }

    getBookById(id: number): Observable<Book> {
        //const url = `${this.booksUrl}/id?id=${id}`;
        const url = `${this.localhost}/book/id?id=${id}`;
        return this.http.get<Book>(url);
    }

    createBook(book: Book): Observable<Book> {
        const url = `${this.localhost}/book`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        //const url = `${this.booksUrl}`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        console.log(book);
        return this.http.post<Book>(url, book);
    }

    updateBook(book: Book): Observable<Book> {
        //const url = `${this.booksUrl}/update`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/book/update`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        return this.http.post<Book>(url, book);
    }

    getAnnouncements(): Observable<Announcement[]> {
        const url = `${this.localhost}/announcements/all`;
        //const url = `${this.announcementsUrl}/all`;
        return this.http.get<Announcement[]>(url);
    }

    getAnnouncementsByFilter(): Observable<Announcement[]> {
        return null;
    }

    createAnnouncement(announcement: Announcement): Observable<Announcement> {
        //const url = `${this.announcementsUrl}/newAnnouncement`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/announcements/newAnnouncement`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        return this.http.post<Announcement>(url, announcement);
    }

    getAnnouncementsUnPublish(): Observable<Announcement[]> {
        //const url = `${this.announcementsUrl}/new`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/announcements/new`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        return this.http.get<Announcement[]>(url);
    }

    publishAnnouncement(announcement: Announcement): Observable<Announcement> {
        //const url = `${this.announcementsUrl}/publish`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/announcements/publish`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;;
        return this.http.post<Announcement>(url, announcement);
    }

   addFriend(sender: User,reciever:User): Observable<User> {
       const url = `${this.localhost}/friends/accept` + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        //const url = `${this.localhost}/announcements/publish`;
       const paramsSender = new HttpParams()
           .set('sender', sender.id.toString()).set('reciever', reciever.id.toString());

        return this.http.post<User>(url, paramsSender);
    }
    getFriends(id:string): Observable<User[]> {
       //const url = `${this.userFriendsUrl}/all`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;;
        const url = `${this.localhost}/friends/all`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const params = new HttpParams()
            .set('id', id);
        return this.http.get<User[]>(url,{params:params});
    }

    getNewApplications(id:string): Observable<User[]> {
        //const url = `${this.userFriendsUrl}/new`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/friends/new`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const params = new HttpParams()
            .set('id', id);
        return this.http.get<User[]>(url,{params:params});
    }

    getAuthorsByBookId(bookId: number): Observable<Author[]>{
        //const url = `${this.booksUrl}/authors/book`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/book/authors/book`;
        const params = new HttpParams()
            .set("book", bookId.toString());
        return this.http.get<Author[]>(url, {params: params});
    }

    getGenreByBookId(bookId: number): Observable<Genre> {
        //const url = `${this.booksUrl}/genre/book`;
        const url = `${this.localhost}/book/genre/book`;
        const params = new HttpParams()
            .set("book", bookId.toString());
        return this.http.get<Genre>(url, {params: params});
    }

    getAllAuthor(): Observable<Author[]> {
        //const url = `${this.booksUrl}/authors`;
        const url = `${this.localhost}/book/authors`;
        return this.http.get<Author[]>(url);
    }

    getAllGenre(): Observable<Genre[]> {
        //const url = `${this.booksUrl}/genres`;
        const url = `${this.localhost}/book/genres`;
        return this.http.get<Genre[]>(url);
    }

    getReviews(id: number): Observable<Review[]> {
        let body = new HttpParams()
            .set('book', id.toString());
        //const url = `${this.reviewsUrl}/all`;
        const url = `${this.localhost}/review/all`;
        return this.http.get<Review[]>(url, {params: body});
    }

    createReview(review: Review): Observable<Review> {
        //const url = `${this.reviewsUrl}`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/review`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        return this.http.post<Review>(url, review);
    }

    getNotAcceptedReviews(bookId: number, status: boolean): Observable<Review[]> {
        const params = new HttpParams()
            .set('book', bookId.toString());
        //const url = `${this.reviewsUrl}/accepted`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/review/notaccepted`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        console.log(params);
        return this.http.get<Review[]>(url, {params: params});
    }

    getAcceptedReviews(bookId: number, status: boolean): Observable<Review[]> {
        const params = new HttpParams()
            .set('book', bookId.toString());
        //const url = `${this.reviewsUrl}/accepted`;
        const url = `${this.localhost}/review/accepted`;
        console.log(params);
        return this.http.get<Review[]>(url, {params: params});
    }


    acceptReview(review: Review): Observable<Review> {
        //const url = `${this.reviewsUrl}/accept`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/review/accept`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        return this.http.post<Review>(url, review);
    }

    deleteReviewById(review: Review): Observable<Review> {
        const params = new HttpParams()
            .set('review', review.id.toString());
        //const url = `${this.reviewsUrl}/delete`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/review/delete`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        console.log(params);
        return this.http.post<Review>(url, params);
    }

    getReviewById(reviewId: number) {
        //const url = `${this.reviewsUrl}/detail`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/review/detail`;
        const params = new HttpParams()
            .set('review', reviewId.toString());
        return this.http.get<Review>(url, {params: params});
    }

    addBookToUser(userBook: UserBook): Observable<UserBook> {
        //const url = `${this.userBookUrl}/add`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/add`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    getAllUserBooks(userBook: UserBook): Observable<Book[]> {
        //const url = `${this.userBookUrl}/all`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/all`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const params = new HttpParams()
            .set('userId', userBook.userId.toString());
        return this.http.get<Book[]>(url, {params: params});
    }

    getUserBookById(userId: number, bookId: number) {
        //const url = `${this.userBookUrl}/getById`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/getById`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        let form = new FormData();
        form.append("userId",userId.toString());
        form.append("bookId",bookId.toString());
        return this.http.post<UserBook>(url, form);
    }

    markUserBookAsRead(userBook: UserBook): Observable<UserBook> {
        //const url = `${this.userBookUrl}/mark_read`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/mark_read`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        userBook.isRead = true;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    markUserBookAsFavourite(userBook: UserBook): Observable<UserBook> {
        //const url = `${this.userBookUrl}/mark_fav`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/mark_fav`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        userBook.isFavorite = true;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    getAllFavouriteBooks(userBook: UserBook): Observable<Book[]> {
        const params = new HttpParams()
            .set('userId', userBook.userId.toString());
        //const url = `${this.userBookUrl}/all/favourite`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/all/favourite`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        return this.http.get<Book[]>(url, {params: params});
    }

    getAllReadBooks(userBook: UserBook): Observable<Book[]>{
        const params = new HttpParams()
            .set('userId',userBook.userId.toString());
        //const url = `${this.userBookUrl}/all/read`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/all/read`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        return this.http.get<Book[]>(url, {params:params});
    }

    removeFromFavourite(userBook: UserBook): Observable<UserBook>{
        //const url = `${this.userBookUrl}/remove_fav`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/remove_fav`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        userBook.isFavorite = false;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    removeFromRead(userBook: UserBook): Observable<UserBook>{
        //const url = `${this.userBookUrl}/remove_read`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/remove_read`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        userBook.isRead = false;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    deleteFromAdded(userBook: UserBook): Observable<UserBook>{
        //const url = `${this.userBookUrl}/delete`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/userBook/delete`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        console.log(userBook);
        return this.http.post<UserBook>(url, userBook);
    }

    makeSuggestion(userId: number):Observable<Book[]>{
        //const url = `${this.booksUrl}/suggestion?user=${userId}`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.localhost}/book/suggestion?user=${userId}`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        return this.http.get<Book[]>(url);

    }

    getMostRatedBooks():Observable<Book[]>{
        //const url = `${this.booksUrl}/rate`;
        const url = `${this.localhost}/book/rate`;
        return this.http.get<Book[]>(url);
    }

    getImageByBook(book:Book){
        //const url = `${this.booksUrl}/bookImage`;
        const url = `${this.localhost}/book/bookImage`;
        return this.http.post(url, book,{responseType: 'blob'});
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
