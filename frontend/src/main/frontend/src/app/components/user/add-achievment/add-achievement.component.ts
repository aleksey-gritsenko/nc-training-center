import {Component, OnInit} from '@angular/core';
import {Achievement} from "../../../models/achievement";
import {AchievementService} from "../../../services/achievement/achievement.service";

@Component({
    selector: 'app-add-achievment',
    templateUrl: './add-achievement.component.html',
    styleUrls: ['./add-achievement.component.css']
})
export class AddAchievementComponent implements OnInit {

    model: Achievement = new Achievement();
    certainActionList: Object[];
    genreList: string[] = [];
    addStatus: string;
    private actionList: Object[] = [];

    constructor(private achievementService: AchievementService) {
        this.achievementService.getActions().toPromise().then(
            actions => {
                this.actionList = actions;
            },
            () => {
                this.addStatus = 'download-error';
            }
        );
        this.achievementService.getGenres().toPromise().then(
            genres => {
                // console.log(genres);
                this.genreList = genres;
            },
            () => {
                this.addStatus = 'download-error';
            }
        )
    }

    ngOnInit() {
    }

    chooseActionsByEntity() {
        this.certainActionList = [];
        let _this = this;
        this.actionList.forEach(function (value) {
            if (value['entity'] == _this.model.entity) {
                _this.certainActionList.push(value);
                _this.model.action = _this.certainActionList[0]['actionName'];
            }
        })
    }

    createAchievement() {
        if (!this.model.isComplete()) this.addStatus = 'warning';
        else this.achievementService.createAchievement(this.model).toPromise().then(
            () => {
                this.addStatus = 'success';
            },
            () => {
                this.addStatus = 'error';
            }
        );
    }
}
