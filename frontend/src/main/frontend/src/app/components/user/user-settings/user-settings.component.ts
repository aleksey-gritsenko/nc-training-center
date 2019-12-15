import {Component, Input, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {UserSettings} from "../../../models/user-settings";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
    selector: 'app-user-settings',
    templateUrl: './user-settings.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css', './user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {

    // private siteUrl: string = 'https://nc-group1-2019.herokuapp.com';
    private siteUrl: string = 'http://localhost:8080';


    constructor(private http: HttpClient, private storage: StorageService) {
    }


    settingsData = [
        {value: true, name: 'notify about friends announcements'},
        {value: true, name: 'notify about friends actions with books'},
        {value: true, name: 'notify about friends achievements'},
        {value: true, name: 'notify about new friends'},
        {value: true, name: 'notify about friends reviews'},
    ];

    ngOnInit() {
        let form = new FormData();
        form.append('userId', String(this.storage.getUser().id));
        const params = new HttpParams()
            .set('userId', String(this.storage.getUser().id));

        this.http.get<UserSettings>(this.siteUrl + '/getSettings' + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, {params: params}).subscribe(
            settings => {
                this.settingsData[0].value = settings.notifyAboutAnnouncements;
                this.settingsData[1].value = settings.bookNotification;
                this.settingsData[2].value = settings.achievements;
                this.settingsData[3].value = settings.notifyAboutNewFriends;
                this.settingsData[4].value = settings.subscribeOnFriendReview;
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
    }

    submitSettings() {
        let form = new FormData();
        var myString: string = <string><any>this.settingsData[0].value;
        form.append('subscribeOnFriends', String(this.settingsData[0].value));
        form.append('bookNotification', String(this.settingsData[1].value));
        form.append('achivements', String(this.settingsData[2].value));
        form.append('notifyAboutNewFriends', String(this.settingsData[3].value));
        form.append('subscribeOnFriendReview', String(this.settingsData[4].value));
        form.append('userId', String(this.storage.getUser().id));

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
