import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CommonService} from '../common/common.service';
import {User} from "../../models/user";
import {Book} from "../../models/book";
import {Announcement} from "../../models/announcement";
import {Review} from "../../models/review";
import {Chat} from "../../models/chat";
import {Message} from "../../models/message";
import {BookFilter} from "../../models/bookfilter";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    // private userUlr = '/user';
    // private usersUlr = '/users/:login';
    user: User;
    friend: User;
    book: Book;

    constructor(private http: HttpClient, private commonService: CommonService) {
    }

    // Personal methods
    updateProfile(user: User) {
        let url = 'http://localhost:8080/user/update';
        let form = new FormData();

        form.append('login', user.userName);
        form.append('newPassword', user.userPassword);
        form.append('newEmail', user.email);

        return this.http.post<User>(url, form);
    }

    searchUser(id: string) {
        const url = "http://localhost:8080/user/" + id;
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

    /*getBookByName(name: string) {
        this.commonService.getBookByName(name);
    }*/

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

    equals(user1: User, user2: User): boolean {
        return user1.email == user2.email && user1.userPassword == user2.userPassword;
    }

    //#endregion
}
