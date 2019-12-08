import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../models/user";
import {Achievement} from "../../../models/achievement";
import {AchievementService} from "../../../services/achievement/achievement.service";
import {CommonService} from "../../../services/common/common.service";

@Component({
    selector: 'app-user-achievement',
    templateUrl: './user-achievement.component.html',
    styleUrls: ['./user-achievement.component.css']
})
export class UserAchievementComponent implements OnInit {
    @Input() private user: User;
    achievements:Achievement[];
    constructor(private achievementService:AchievementService, private commonService:CommonService) { }

    ngOnInit() {
        this.getAllAchievement();
    }

    getAllAchievement(){
        this.achievementService.getUserAchievement(this.user.id).subscribe(
            res=>{this.achievements = res;}
        );

    }
}


