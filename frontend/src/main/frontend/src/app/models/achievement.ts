export class Achievement {
    achievementName: string;
    count: number;
    entity: string;
    genre: string;
    action: string;
    description:string[]=[];
    genreName:string;

    constructor() {
        this.achievementName='';
        this.entity='';
        this.genre='';
        this.action='';
    }

    isComplete(): boolean {
        return this.achievementName.length>0 && this.count>0 && this.action.length>0 && ((this.entity=='book' && this.genre.length>0) || (this.entity!='book' && this.entity!=''));
    }
}
