import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RecoverComponent} from './components/recover/recover.component';
import {ChangeComponent} from './change/change.component';
import {LoginComponent} from "./components/login/login.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {LandingComponent} from "./components/landing/landing.component";
import {ErrorpageComponent} from "./components/errorpage/errorpage.component";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";
import {BooksListComponent} from "./components/books-list/books-list.component";
import {BookComponent} from "./components/book/book.component";
import {AnnouncementComponent} from "./components/announcement/announcement.component";
const routes: Routes = [
    {
        path: 'recover',
        component: RecoverComponent
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
        component:BooksListComponent
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
        path: 'user/:userName',
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

