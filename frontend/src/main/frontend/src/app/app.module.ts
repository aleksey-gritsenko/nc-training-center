import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { CommonModule } from '@angular/common';
import { FlatpickrModule } from 'angularx-flatpickr';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigationComponent} from './components/navigation/navigation.component';
import {LoginComponent} from './components/authorization/login/login.component';
import {RegistrationComponent} from './components/authorization/registration/registration.component';
import {LandingComponent} from './components/landing/landing.component';
import {ErrorpageComponent} from './components/errorpage/errorpage.component';
import {UserProfileComponent} from './components/user/user-profile/user-profile.component';
import {AnnouncementListComponent} from './components/announcement-list/announcement-list.component';
import {BooksListComponent} from './components/books-list/books-list.component';
import {ReviewsListComponent} from './components/reviews-list/reviews-list.component';
import {BookOverviewPropositionComponent} from './components/book-overview-proposition/book-overview-proposition.component';
import {BookReviewPropositionComponent} from './components/book-review-proposition/book-review-proposition.component';
import {AnnouncementPropositionComponent} from './components/announcement-proposition/announcement-proposition.component';
import {BookComponent} from './components/book/book.component';
import {ReviewComponent} from './components/review/review.component';
import {AnnouncementComponent} from './components/announcement/announcement.component';
import {ActivityComponent} from './components/activity/activity.component';
import {ActivitiesListComponent} from './components/activities-list/activities-list.component';
import {FriendsListComponent} from './components/friends-list/friends-list.component';
import {ModeratorsListComponent} from './components/moderators-list/moderators-list.component';
import {AdminsListComponent} from './components/admins-list/admins-list.component';
import {ChatComponent} from './components/chat/chat.component';
import {CalendarComponent} from './components/calendar/calendar.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {AuthenticationService} from './services/authentification/authentication.service';
import {RecoverComponent} from './components/authorization/recover/recover.component';
import {ChangePasswordComponent} from './components/authorization/change-password/change-password.component';
import {ViewProfileComponent} from './components/user/view-profile/view-profile.component';
import {EditProfileComponent} from './components/user/edit-profile/edit-profile.component';
import {AddAdminComponent} from './components/user/add-admin/add-admin.component';
import {DeactivateAccountComponent} from './components/user/deactivate-account/deactivate-account.component';
import {CKEditorModule} from "@ckeditor/ckeditor5-angular";
import {TruncateTextPipe} from './components/pipe/truncate-text.pipe';
import {UserBooksComponent} from './components/user/user-books/user-books.component';
import {ConfirmEmailComponent} from './components/authorization/confirm-email/confirm-email.component';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { UserFavouriteBooksComponent } from './components/user/user-favourite-books/user-favourite-books.component';
import { UserReadBooksComponent } from './components/user/user-read-books/user-read-books.component';
import {SearchComponent} from './components/search/search.component';
import {AddAchievmentComponent} from './components/user/add-achievment/add-achievment.component';
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import { BookImgComponent } from './components/book-img/book-img.component';

//import {NotificationsListComponent} from "./components/notifications-list/notifications-list.component";

@NgModule({
    declarations: [
        AppComponent,
        NavigationComponent,
        LoginComponent,
        RegistrationComponent,
        LandingComponent,
        ErrorpageComponent,
        UserProfileComponent,
        AnnouncementListComponent,
        BooksListComponent,
        ReviewsListComponent,
       // NotificationsListComponent,
        BookOverviewPropositionComponent,
        BookReviewPropositionComponent,
        AnnouncementPropositionComponent,
        BookComponent,
        ReviewComponent,
        AnnouncementComponent,
        ActivityComponent,
        ActivitiesListComponent,
        FriendsListComponent,
        ModeratorsListComponent,
        AdminsListComponent,
        ChatComponent,
        CalendarComponent,
        SidebarComponent,
        RecoverComponent,
        ChangePasswordComponent,
        ViewProfileComponent,
        EditProfileComponent,
        AddAdminComponent,
        DeactivateAccountComponent,
        TruncateTextPipe,
        ConfirmEmailComponent,
        UserFavouriteBooksComponent,
        UserReadBooksComponent,
        UserBooksComponent,
        SearchComponent,
        AddAchievmentComponent,
        FileUploadComponent,
        BookImgComponent
    ],

    imports: [
        CommonModule,
        NgbModalModule,
        FlatpickrModule.forRoot(),
        BrowserModule,
        HttpClientModule,
        FormsModule,
        AppRoutingModule,
        CKEditorModule,
        ReactiveFormsModule,
        MatButtonToggleModule,
        BrowserAnimationsModule,
        CalendarModule.forRoot({
            provide: DateAdapter,
             useFactory: adapterFactory})
    ],

    providers: [AuthenticationService],
    bootstrap: [AppComponent],
    exports: [CalendarComponent]
})

export class AppModule {
}
