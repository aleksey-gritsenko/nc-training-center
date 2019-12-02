import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {AuthenticationService} from "../../../services/authentification/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-confirm-email',
  templateUrl: './confirm-email.component.html',
  styleUrls: ['../../../resources/styles/Authorization.css']
})
export class ConfirmEmailComponent implements OnInit {
  code: string = '';
  email: string = '';
  message: string;

  constructor(private storageService: StorageService,
              private authenticationService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
  }

  confirm() {
      if (this.email.length != 0 && this.code.length != 0) {
          this.authenticationService.confirmEmail(this.email, this.code)
              .toPromise().then(user => {
                  this.storageService.setUser(user);
                  this.router.navigateByUrl('/')
              },
              error => {
                  this.message = 'error';
              });
      }
  }

  resend() {
      if (this.email.length != 0) {
          this.authenticationService.resendCode(this.email).toPromise()
              .then(
                  result => {
                      this.message = 'send';
                  },
                  error => {
                      this.message = 'error';
                  });
      }
  }

}
