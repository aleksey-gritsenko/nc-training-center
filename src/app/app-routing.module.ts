<<<<<<< HEAD
import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {LandingComponent} from './landing/landing.component';
import {ErrorpageComponent} from './errorpage/errorpage.component';
import {RecoverComponent} from './recover/recover.component';
import {ChangeComponent} from './change/change.component';
=======
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {LandingComponent} from "./components/landing/landing.component";
import {ErrorpageComponent} from "./components/errorpage/errorpage.component";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";
>>>>>>> 6370a182546e95818761b382343272ba4d99af66

const routes: Routes = [{
  path: 'login',
  component: LoginComponent
},
<<<<<<< HEAD
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'home',
    component: LandingComponent
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
{
path:'registration',
component:RegistrationComponent
},
{
path:'home',
component: LandingComponent
},
{
path: 'user',
component: UserProfileComponent
},
{
path:'',
component: LandingComponent,
pathMatch:'full'
},
{
path:'**',
component:ErrorpageComponent
}
>>>>>>> 6370a182546e95818761b382343272ba4d99af66
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
<<<<<<< HEAD
export class AppRoutingModule {
}
=======
export class AppRoutingModule { }
>>>>>>> 6370a182546e95818761b382343272ba4d99af66
