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
    allGenres: Genre[] = [];
    @Input() private user: User;

    constructor(private achievementService: AchievementService,
                private commonService: CommonService) {
    }

    ngOnInit() {
        this.commonService.getAllGenre().subscribe(
            res => this.allGenres
        );
        this.getAllAchievement();
    }

    getAllAchievement() {
        this.achievementService.getUserAchievement(this.user).subscribe(
            res => {
                this.achievements = res;
            }
        );
    }

}
