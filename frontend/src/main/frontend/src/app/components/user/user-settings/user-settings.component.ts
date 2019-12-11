import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserSettings} from "../../../models/user-settings";

@Component({
    selector: 'app-user-settings',
    templateUrl: './user-settings.component.html',
    styleUrls: ['./user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {
    items = ["Apple iPhone 7", "Huawei Mate 9", "Samsung Galaxy S7", "Motorola Moto Z"];
    settingsData = [
        {value: true, name: 'subscribeOnFriends'},
        {value: true, name: 'achivements'},
        {value: true, name: 'bookNotification'},
        {value: true, name: 'subscribeOnFriendReview'},
        {value: true, name: 'notifyAboutNewFriends'},
        {value: true, name: 'notifyAboutAchievement'},
    ];
    private siteUrl: string = 'https://nc-group1-2019.herokuapp.com';

    constructor(private http: HttpClient) {
    }

    ngOnInit() {
        let form = new FormData();
        form.append('userId', '5');
        this.http.post<UserSettings>(this.siteUrl + '/getSettings' + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, form).subscribe(
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

    }


}
