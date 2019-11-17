import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
<<<<<<< HEAD
import {LoginComponent} from './components/login/login.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {LandingComponent} from './components/landing/landing.component';
import {ErrorpageComponent} from './components/errorpage/errorpage.component';
import {UserProfileComponent} from './components/user-profile/user-profile.component';
import {RecoverComponent} from './components/recover/recover.component';

const routes: Routes = [{
  path: 'login',
  component: LoginComponent
},
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'home',
    component: LandingComponent
  },
  {
    path: 'user',
    component: UserProfileComponent
  },
  {
    path: 'recover',
    component: RecoverComponent
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
=======
import {RecoverComponent} from './components/recover/recover.component';
import {ChangeComponent} from './change/change.component';
import {LoginComponent} from "./components/login/login.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {LandingComponent} from "./components/landing/landing.component";
import {ErrorpageComponent} from "./components/errorpage/errorpage.component";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";

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
>>>>>>> 138d052c6e33e70be10edad21e3201db95af2e11
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {
}

