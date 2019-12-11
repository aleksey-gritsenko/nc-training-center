export class Achievement {
    achievementName: string;
    count: number;
    entity: string;
    genreId: number;
    actionTypeId: number;

    constructor() {
        this.achievementName='';
        this.entity='';
    }

    isComplete(): boolean {
        return this.achievementName.length>0 && this.count>0 && this.actionTypeId>0 && ((this.entity=='book' && this.genreId>0) || (this.entity!='book' && this.entity!=''));
    }
}
