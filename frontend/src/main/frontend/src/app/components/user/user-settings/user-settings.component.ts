import {Component, Input, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {UserSettings} from "../../../models/user-settings";

@Component({
    selector: 'app-user-settings',
    templateUrl: './user-settings.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css', './user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {

    // private siteUrl: string = 'https://nc-group1-2019.herokuapp.com';
    private siteUrl: string = 'http://localhost:8080';


    constructor(private http: HttpClient) {
    }


    settingsData = [
        {value: true, name: 'subscribeOnFriends'},
        {value: true, name: 'achivements'},
        {value: true, name: 'bookNotification'},
        {value: true, name: 'subscribeOnFriendReview'},
        {value: true, name: 'notifyAboutNewFriends'},
        {value: true, name: 'notifyAboutAchievement'},
    ];

    ngOnInit() {
        let form = new FormData();
        form.append('userId', '5');
        const params = new HttpParams()
            .set('userId', '5');

        this.http.get<UserSettings>(this.siteUrl + '/getSettings' + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, {params: params}).subscribe(
            settings => {
                this.settingsData[0].value = settings.subscribeOnFriends;
                this.settingsData[1].value = settings.achievements;
                this.settingsData[2].value = settings.bookNotification;
                this.settingsData[3].value = settings.subscribeOnFriendReview;
                this.settingsData[4].value = settings.notifyAboutNewFriends;
                this.settingsData[5].value = settings.notifyAboutAchievement;
            },
            error1 => {
                alert('Error')
            }
        );
    }

    onToggle(name: string, value: boolean) {

        let reverseValue: boolean = true;
        if (value === true) {
            reverseValue = false;
        }
        this.settingsData.filter(data => data.name === name).map(dt => dt.value = reverseValue);
        alert(this.settingsData[0].value);
    }

    submitSettings() {
        let form = new FormData();
        var myString: string = <string><any>this.settingsData[0].value;
        form.append('subscribeOnFriends', String(this.settingsData[0].value));
        form.append('achivements', String(this.settingsData[1].value));
        form.append('bookNotification', String(this.settingsData[2].value));
        form.append('subscribeOnFriendReview', String(this.settingsData[3].value));
        form.append('notifyAboutNewFriends', String(this.settingsData[4].value));
        form.append('notifyAboutAchievement', String(this.settingsData[5].value));
        form.append('userId', '5');

        this.http.post(this.siteUrl + '/updateSettings' + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, form).subscribe(
            data => {
                //    this.router.navigate([this.returnUrl]);
            },
            error1 => {
                alert('Error in submit');
            }
        );
    }


}
