export class Achievement {
    name: string;
    count: number;
    entity: string;
    genre_id: string;
    action: string;
    description:string[]=[];


    constructor() {
        this.name='';
        this.entity='';
        this.genre_id='';
        this.action='';
    }

    isComplete(): boolean {
        return this.name.length>0 && this.count>0 && this.action.length>0 && ((this.entity=='book' && this.genre_id.length>0) || (this.entity!='book' && this.entity!=''));
    }
}