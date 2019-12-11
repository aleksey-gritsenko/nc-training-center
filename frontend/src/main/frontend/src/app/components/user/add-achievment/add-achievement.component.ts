import {Component, OnDestroy, OnInit} from '@angular/core';
import {Achievement} from "../../../models/achievement";
import {AchievementService} from "../../../services/achievement/achievement.service";
import {Subscription} from "rxjs";
import {Genre} from "../../../models/genre";
import {CommonService} from "../../../services/common/common.service";
import {Action} from "../../../models/action";

@Component({
    selector: 'app-add-achievment',
    templateUrl: './add-achievement.component.html',
    styleUrls: ['./add-achievement.component.css']
})
export class AddAchievementComponent implements OnInit, OnDestroy {
    actionSubscription: Subscription;
    genresSubscription: Subscription;
    creationSubscription: Subscription;

    model: Achievement = new Achievement();

    actionList: Action[] = [];
    certainActionList: Action[];
    genreList: Genre[] = [];

    addStatus: string;

    constructor(private achievementService: AchievementService,
                private commonService: CommonService) {
    }

    ngOnInit() {
        this.actionSubscription = this.achievementService.getActions().subscribe(
            actions => {
                this.actionList = actions;
            },
            () => {
                this.addStatus = 'download-error';
            }
        );
        this.genresSubscription = this.commonService.getAllGenre().subscribe(
            genres => {
                this.genreList = genres;
            },
            () => {
                this.addStatus = 'download-error';
            }
        )
    }

    ngOnDestroy(): void {
        if (this.genresSubscription) {
            this.genresSubscription.unsubscribe();
        }
        if (this.actionSubscription) {
            this.actionSubscription.unsubscribe();
        }
        if (this.creationSubscription) {
            this.creationSubscription.unsubscribe();
        }
    }

    chooseActionsByEntity() {
        this.certainActionList = [];
        this.actionList.forEach(value => {
            if (value.entity == this.model.entity) {
                this.certainActionList.push(value);
            }
        });
        this.model.actionTypeId = this.certainActionList[0].actionTypeId;
    }

    createAchievement() {
        // if (!this.model.isComplete()) {
        //     this.addStatus = 'warning';
        // }
        // else {
        //     this.creationSubscription = this.achievementService.createAchievement(this.model).subscribe(
        //         () => {
        //             this.addStatus = 'success';
        //         },
        //         () => {
        //             this.addStatus = 'error';
        //         }
        //     );
        // }
        console.log(this.model);
    }
}
