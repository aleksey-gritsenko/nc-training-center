import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../models/user";
import {Achievement} from "../../../models/achievement";
import {AchievementService} from "../../../services/achievement/achievement.service";
import {CommonService} from "../../../services/common/common.service";
import {Genre} from "../../../models/genre";
import {UserService} from "../../../services/user/user.service";
import {UserSettings} from "../../../models/user-settings";
import {ActionEntity} from "../../../models/action-entity";


@Component({
    selector: 'app-user-achievement',
    templateUrl: './user-achievement.component.html',
    styleUrls: ['./user-achievement.component.css']
})
export class UserAchievementComponent implements OnInit {
    private settings = new Map<string,boolean>();

    ActionEntity = ActionEntity;
    achievements: Achievement[] = [];
    resultAchievement:Achievement[] = [];
    allGenres: Genre[] = [];
    userSettings:UserSettings = new UserSettings();
    @Input() private user: User;

    constructor(private achievementService: AchievementService,
                private commonService: CommonService,
                private userService:UserService) {
    }

    ngOnInit() {
        this.commonService.getAllGenre().subscribe(
            res => this.allGenres
        );
        this.getUserSettings();
        this.getAllAchievement();
        this.settings.set(ActionEntity.book,this.userSettings.bookNotification);
        this.settings.set(ActionEntity.achievement, this.userSettings.notifyAboutAchievement);
        this.settings.set(ActionEntity.review, this.userSettings.subscribeOnFriendReview);
        this.settings.set(ActionEntity.user, this.userSettings.subscribeOnFriends);
        this.checkUserSettings();
    }

    getAllAchievement() {
        this.achievementService.getUserAchievement(this.user).subscribe(
            res => {
                this.achievements = res;
            }
        );
    }

    getUserSettings(){
        this.userService.getUserSettings(this.user.id).subscribe(
            res=>{
                this.userSettings = res;
                console.log(res)
            }
        )
    }

    checkUserSettings(){
        this.achievements.forEach(achievement=>{
            if(this.settings.get(achievement.entity)) {
                this.resultAchievement.push(achievement);
            }
        });

    }
}
