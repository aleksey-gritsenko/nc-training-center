import { Injectable } from '@angular/core';
import { CommonService } from '../common/common.service';
import { SearchService } from '../search/search.service';
import { Announcement } from 'src/app/models/announcement';
import { Book } from 'src/app/models/book';
import { Review } from 'src/app/models/review';
import { User } from 'src/app/models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ModeratorService {

  announcement : Announcement;
  overview : Book;

  constructor(
    private commonService : CommonService,
    private searchService : SearchService,
    private http : HttpClient,
  ) { }

  // Personal methods

  publishAnnouncement(){

  }

  publishBookOverview(){

  }

  publishBookReview(book : Book, review : Review){

  }

  removeAnnouncement(){

  }

  removeBookOverview(){

  }

  removeBookReview(book : Book, review : Review){

  }

  manageAnnouncement(){

  }

  manageBookOverview(){

  }

  deactivateUser(user : User){

  }

  //#region Services Methods 

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