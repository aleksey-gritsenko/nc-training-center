import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {CommonService} from '../common/common.service';
import {ManagingService} from '../managing/managing.service';
import {SearchService} from '../search/search.service';
import {Book} from "../../models/book";
import {HttpClient} from '@angular/common/http';
import {BookFilter} from "../../models/bookfilter";
import {AdminRightsDto} from "../../models/admin-rights-dto";


@Injectable({
    providedIn: 'root'
})
export class AdminService {
private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';
// if you want to test your code on localhost - change siteUrl to localhost where this is needed
private localhost: string = 'http://localhost:8080';
private adminRightsUrl: string = `${this.localhost}/admin-rights`;
    constructor(
        private commonService: CommonService,
        private managingService: ManagingService,
        private searchService: SearchService,
        private http: HttpClient,
    ) {
    }

    //#region Services Methods
    getAllRights():Observable<AdminRightsDto[]>{
        const url = `${this.adminRightsUrl}/get`;
        return this.http.get<AdminRightsDto[]>(url);
    }

    getAllRightTypes(): Observable<string[]>{
        const url = `${this.adminRightsUrl}/get-all-descriptions`;
        return this.http.get<string[]>(url);
    }

    setAllRights(rights: AdminRightsDto[]):void{
        const url = `${this.adminRightsUrl}/set`;
        this.http.post(url,rights).subscribe(res=>{
            console.log(res);
        },
        err=>{console.log(err)});
    }
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

    /*getBookByName(name: string) {
        this.commonService.getBookByName(name);
    }*/

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
