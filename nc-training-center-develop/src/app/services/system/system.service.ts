import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Achievement } from 'src/app/models/achievement';

@Injectable({
  providedIn: 'root'
})
export class SystemService {

  user : User;

  constructor(
    private http : HttpClient,
  ) { }

  register(){

  }

  notify(user : User, notification : Notification){

  }

  giveAchievement(achievement : Achievement){

  }
}
