import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LandingComponent } from './components/landing/landing.component';
import { ErrorpageComponent } from './components/errorpage/errorpage.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AnnouncementListComponent } from './components/announcement-list/announcement-list.component';
import { BooksListComponent } from './components/books-list/books-list.component';
import { ReviewsListComponent } from './components/reviews-list/reviews-list.component';
import { NotificationsListComponent } from './components/notifications-list/notifications-list.component';
import { BookOverviewPropositionComponent } from './components/book-overview-proposition/book-overview-proposition.component';
import { BookReviewPropositionComponent } from './components/book-review-proposition/book-review-proposition.component';
import { AnnouncementPropositionComponent } from './components/announcement-proposition/announcement-proposition.component';
import { BookComponent } from './components/book/book.component';
import { ReviewComponent } from './components/review/review.component';
import { AnnouncementComponent } from './components/announcement/announcement.component';
import { ActivityComponent } from './components/activity/activity.component';
import { ActivitiesListComponent } from './components/activities-list/activities-list.component';
import { FriendsListComponent } from './components/friends-list/friends-list.component';
import { SuperadminProfileComponent } from './components/superadmin-profile/superadmin-profile.component';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { ModeratorProfileComponent } from './components/moderator-profile/moderator-profile.component';
import { ModeratorsListComponent } from './components/moderators-list/moderators-list.component';
import { AdminsListComponent } from './components/admins-list/admins-list.component';
import { ChatComponent } from './components/chat/chat.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { AuthenticationService } from "./services/authentification/authentication.service";
<<<<<<< HEAD
import { RecoverComponent } from './components/recover/recover.component';
=======
import {RecoverComponent} from "./components/recover/recover.component";
>>>>>>> 138d052c6e33e70be10edad21e3201db95af2e11

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
    NotificationsListComponent,
    BookOverviewPropositionComponent,
    BookReviewPropositionComponent,
    AnnouncementPropositionComponent,
    BookComponent,
    ReviewComponent,
    AnnouncementComponent,
    ActivityComponent,
    ActivitiesListComponent,
    FriendsListComponent,
    SuperadminProfileComponent,
    AdminProfileComponent,
    ModeratorProfileComponent,
    ModeratorsListComponent,
    AdminsListComponent,
    ChatComponent,
    CalendarComponent,
    SidebarComponent,
    RecoverComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
