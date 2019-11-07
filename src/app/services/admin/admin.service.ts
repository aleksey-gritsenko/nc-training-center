import { Injectable } from '@angular/core';
import { CommonService } from '../common/common.service';
import { ManagingService } from '../managing/managing.service';
import { SearchService } from '../search/search.service';
import { Book } from 'src/app/models/book';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(
    private commonService : CommonService,
    private managingService : ManagingService,
    private searchService : SearchService,
    private http : HttpClient,
  ) { }

 //#region Services Methods

  // Managing service
  createModerator(){
    this.managingService.createModerator();
  }

  manageModerator(){
    this.managingService.manageModerator();
  }

  deactivateModerator(){
    this.managingService.deactivateModerator();
  }

  createAchievement(){
    this.managingService.createAchievement();
  }

  // Common service
  getBooks(){
    this.commonService.getBooks();
  }

  getBooksByFilter(){
    this.commonService.getBooksByFilter();
  }

  getBookByName(name : string){
    this.commonService.getBookByName(name);
  }

  getAnnouncements(){
    this.commonService.getAnnouncements();
  }

  getAnnouncementsByFilter(){
    this.commonService.getAnnouncementsByFilter();
  }

  getReviews(book : Book){
    this.commonService.getReviews(book);
  }

  recoverPassword(){
    this.commonService.recoverPassword();
  }

  manageNotifications(){
    this.commonService.manageNotifications();
  }

  login(){
    this.commonService.login();
  }

  // Search service
  searchProfile(profile : string){
    this.searchService.searchProfile(profile);
  }

  //#endregion
}
