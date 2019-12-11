import {Injectable} from '@angular/core';
import {CommonService} from '../common/common.service';
import {ManagingService} from '../managing/managing.service';
import {SearchService} from '../search/search.service';
import {HttpClient} from '@angular/common/http';
import {BookFilter} from "../../models/bookfilter";
import {Book} from "../../models/book";

@Injectable({
    providedIn: 'root'
})
export class SuperadminService {
    // extends CommonService, ManagingService, SearchService {

    constructor(
        private commonService: CommonService,
        private managingService: ManagingService,
        private searchService: SearchService,
        private http: HttpClient,
    ) {
    }

    // Personal methods
    createAdmin() {

    }

    manageAdmin() {

    }

    deactivateAdmin() {

    }

    //#region Services Methods

    // Managing service
    createModerator() {
        this.managingService.createModerator();
    }

    manageModerator() {
        this.managingService.manageModerator();
    }

    deactivateModerator() {
        this.managingService.deactivateModerator();
    }

    createAchievement() {
        this.managingService.createAchievement();
    }

    // Common service
    getBooks() {
        this.commonService.getBooks();
    }

    getBooksByFilter(filter: BookFilter) {
        this.commonService.getBooksByFilter(filter);
    }

    // getBookByName(name : string){
    //   this.commonService.getBookByName(name);
    // }

    getAnnouncements() {
        this.commonService.getAnnouncements();
    }

    getAnnouncementsByFilter() {
        this.commonService.getAnnouncementsByFilter();
    }

    getReviews(book: Book) {
        this.commonService.getReviews(book.id);
    }

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
