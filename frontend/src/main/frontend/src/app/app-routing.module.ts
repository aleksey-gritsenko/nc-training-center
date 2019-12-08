import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/authorization/login/login.component';
import {RegistrationComponent} from './components/authorization/registration/registration.component';
import {LandingComponent} from './components/landing/landing.component';
import {ErrorpageComponent} from './components/errorpage/errorpage.component';
import {UserProfileComponent} from './components/user/user-profile/user-profile.component';
import {RecoverComponent} from './components/authorization/recover/recover.component';
import {ChangePasswordComponent} from './components/authorization/change-password/change-password.component';
import {BookComponent} from './components/book/book.component';
import {BooksListComponent} from "./components/books-list/books-list.component";
import {AnnouncementComponent} from "./components/announcement/announcement.component";
import {AnnouncementListComponent} from "./components/announcement-list/announcement-list.component";
import {AnnouncementPropositionComponent} from "./components/announcement-proposition/announcement-proposition.component";
import {ReviewComponent} from "./components/review/review.component";
import {UserBooksComponent} from "./components/user/user-books/user-books.component";
import {ConfirmEmailComponent} from "./components/authorization/confirm-email/confirm-email.component";
import {FriendsListComponent} from "./components/friends-list/friends-list.component";
import {CalendarComponent} from "./components/calendar/calendar.component";
import {SearchComponent} from "./components/search/search.component";
import {UserFavouriteBooksComponent} from "./components/user/user-favourite-books/user-favourite-books.component";
import {UserReadBooksComponent} from "./components/user/user-read-books/user-read-books.component";
import {UserSettingsComponent} from "./components/user/user-settings/user-settings.component";

const routes: Routes = [
    {
        path: 'recover',
        component: RecoverComponent
    },
    {
        path: 'announcement',
        component: AnnouncementComponent
    },
    {
        path: 'announcementlist',
        component: AnnouncementListComponent
    },
    {
        path: 'announcementpublish',
        component: AnnouncementPropositionComponent
    },
    {
        path: 'friends',
        component: FriendsListComponent
    },
    {
        path: 'change',
        component: ChangePasswordComponent
    },
    {
        path: 'calendar',
        component: CalendarComponent
    },
    {
        path: 'registration',
        component: RegistrationComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'home',
        component: LandingComponent
    },
    {
        path: 'books',
        component: BooksListComponent
    },
    {
        path: 'user/:id/books',
        component: BooksListComponent
    },
    {
        path: 'user/:id/books/book/:bookId',
        component: BookComponent
    },
    {
        path: 'books/:bookId/announcement',
        component: AnnouncementComponent
    },
    {
        path: 'books/book/:bookId',
        component: BookComponent
    },
    {
        path: 'books/book/:bookId/review/:reviewId',
        component: ReviewComponent
    },
    {
        path: 'user',
        component: UserProfileComponent
    },
    {
        path: 'book',
        component: BookComponent
    },
    {
        path: 'userBooks',
        component: UserBooksComponent
    },
    {
        path: 'userBooks/book/:bookId',
        component: BookComponent
    },
    {
        path: 'userBooks/read',
        component: UserReadBooksComponent
    },
    {
        path: 'userBooks/read/book/:bookId',
        component: BookComponent
    },
    {
        path: 'userBooks/favourite',
        component: UserFavouriteBooksComponent
    },
    {
        path: 'userBooks/favourite/book/:bookId',
        component: BookComponent
    },
    {
        path: 'user/:id',
        component: UserProfileComponent
    },
    {
        path: 'verify',
        component: ConfirmEmailComponent
    },
    {
        path: 'search',
        component: SearchComponent
    },
    {
        path: 'settings',
        component: UserSettingsComponent
    },
    {
        path: 'error',
        component: ErrorpageComponent
    },
    {
        path: '',
        component: LandingComponent,
        pathMatch: 'full'
    },
    {
        path: '**',
        component: ErrorpageComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {
}

