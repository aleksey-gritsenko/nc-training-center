import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {LandingComponent} from './components/landing/landing.component';
import {ErrorpageComponent} from './components/errorpage/errorpage.component';
import {UserProfileComponent} from './components/user-profile/user-profile.component';
import {RecoverComponent} from './components/recover/recover.component';
import {ChangePasswordComponent} from './components/change-password/change-password.component';

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
    path: 'change',
    component: ChangePasswordComponent
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

