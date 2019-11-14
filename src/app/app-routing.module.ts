import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {LandingComponent} from './landing/landing.component';
import {ErrorpageComponent} from './errorpage/errorpage.component';
import {RecoverComponent} from './recover/recover.component';
import {ChangeComponent} from './change/change.component';

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

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
