import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {LandingComponent} from './components/landing/landing.component';
import {ErrorpageComponent} from './components/errorpage/errorpage.component';
import {UserProfileComponent} from './components/user-profile/user-profile.component';
import {RecoverComponent} from './components/recover/recover.component';
import {ChangePasswordComponent} from './components/change-password/change-password.component';
import {BookComponent} from './components/book/book.component';
import {BooksListComponent} from "./components/books-list/books-list.component";
import {AnnouncementComponent} from "./components/announcement/announcement.component";
import {AnnouncementListComponent} from "./components/announcement-list/announcement-list.component";

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
        path: 'change',
        component: ChangePasswordComponent
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
        path: 'books/:id/announcement',
        component: AnnouncementComponent
    },
    {
        path: 'books/:id',
        component: BookComponent
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
        path: 'user/:id',
        component: UserProfileComponent
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

