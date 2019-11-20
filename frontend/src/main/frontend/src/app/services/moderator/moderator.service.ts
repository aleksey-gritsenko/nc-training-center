import {Injectable} from '@angular/core';
import {CommonService} from '../common/common.service';
import {SearchService} from '../search/search.service';
import {HttpClient} from '@angular/common/http';
import {Announcement} from "../../models/announcement";
import {Book} from "../../models/book";
import {Review} from "../../models/review";
import {User} from "../../models/user";

@Injectable({
    providedIn: 'root'
})
export class ModeratorService {

    announcement: Announcement;
    overview: Book;

    constructor(
        private commonService: CommonService,
        private searchService: SearchService,
        private http: HttpClient,
    ) {
    }

    // Personal methods

    publishAnnouncement() {

    }

    publishBookOverview() {

    }

    publishBookReview(book: Book, review: Review) {

    }

    removeAnnouncement() {

    }

    removeBookOverview() {

    }

    removeBookReview(book: Book, review: Review) {

    }

    manageAnnouncement() {

    }

    manageBookOverview() {

    }

    deactivateUser(user: User) {

    }

    //#region Services Methods

    // Common service
    getBooks() {
        this.commonService.getBooks();
    }

    // getBooksByFilter(){
    //   this.commonService.getBooksByFilter();
    // }

    // getBookByName(name : string){
    //   this.commonService.getBookByName(name);
    // }

    getAnnouncements() {
        this.commonService.getAnnouncements();
    }

    // getAnnouncementsByFilter(){
    //   this.commonService.getAnnouncementsByFilter();
    // }

    // getReviews(book : Book){
    //   this.commonService.getReviews(book);
    // }

    recoverPassword() {
        this.commonService.recoverPassword();
    }

    manageNotifications() {
        this.commonService.manageNotifications();
    }

    login() {
        this.commonService.login();
    }

    // Search service
    searchProfile(profile: string) {
        this.searchService.searchProfile(profile);
    }

    //#endregion
}
