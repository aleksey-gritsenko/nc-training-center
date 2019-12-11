import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../models/user";
import {Achievement} from "../../../models/achievement";
import {AchievementService} from "../../../services/achievement/achievement.service";
import {CommonService} from "../../../services/common/common.service";
import {Genre} from "../../../models/genre";

@Component({
    selector: 'app-user-achievement',
    templateUrl: './user-achievement.component.html',
    styleUrls: ['./user-achievement.component.css']
})
export class UserAchievementComponent implements OnInit {
    achievements: Achievement[];
    allGenres: Genre[] = [];
    @Input() private user: User;

    constructor(private achievementService: AchievementService, private commonService: CommonService) {
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
                // this.achievements.forEach(achievement => this.allGenres.forEach(
                    // genre => achievement.genreName = genre.id.toString() == achievement.genre ? genre.name : ""));
                // console.log(this.achievements)  TODO commented, cause it is in achievement model
            }
        );
    }
}
