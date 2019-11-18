import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CommonService} from '../common/common.service';
import {User} from 'src/app/models/user';
import {Book} from 'src/app/models/book';
import {Announcement} from 'src/app/models/announcement';
import {Review} from 'src/app/models/review';
import {Chat} from 'src/app/models/chat';
import {Message} from 'src/app/models/message';
import {BookFilter} from 'src/app/models/bookfilter';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // private userUlr = '/user';
  // private usersUlr = '/users/:login';
  user: User;
  friend: User;
  book: Book;

  constructor(
    private http: HttpClient,
    private commonService: CommonService,
  ) {
  }

  // Personal methods
  updateProfile(login: string , user: User) {
    let url = 'http://localhost:8080/user/update';
    let form = new FormData();

    form.append('login', login);
    form.append('newLogin', user.userName);
    form.append('newPassword', user.userPassword);
    form.append('newEmail', user.email);

    return this.http.post<User>(url, form);
  }

  searchUser(userName: string){
    const url = "http://localhost:8080/user/" + userName;
    return this.http.get<User>(url);
  }

  getFriends() {

  }

  addFriend(friend: User) {

  }

  removeFriend(friend: User) {

  }

  manageFriendActivities() {

  }

  proposeAnnouncement(announcement: Announcement) {

  }

  proposeBookOverview(book: Book) {

  }

  proposeBookReview(book: Book, review: Review) {

  }

  addBookToRead(book: Book) {

  }

  addBookToFavourite(book: Book) {

  }

  removeBookFromRead(book: Book) {

  }

  removeBookFromFavourite(book: Book) {

  }

  manageCalendar() {

  }

  createChat() {

  }

  changeChatName(chat: Chat) {

  }

  addFriendToChat(friend: User, chat: Chat) {

  }

  sendMessage(message: Message) {

  }

  saveHistory() {

  }

  leaveChat() {

  }

  //#region Common service

  getBooks() {
    this.commonService.getBooks();
  }

  getBooksByFilter(filter: BookFilter) {
    this.commonService.getBooksByFilter(filter);
  }

  getBookByName(name: string) {
    this.commonService.getBookByName(name);
  }

  getAnnouncements() {
    this.commonService.getAnnouncements();
  }

  getAnnouncementsByFilter() {
    // this.commonService.getAnnouncementsByFilter();
  }

  getReviews(book: Book) {
    // this.commonService.getReviews(book);
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

  //#endregion
}
