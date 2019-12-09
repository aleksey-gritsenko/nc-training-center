import { Component, OnInit } from '@angular/core';
import { AdminService} from '../../services/admin/admin.service';
import { AdminRightsDto} from '../../models/admin-rights-dto';

@Component({
  selector: 'app-admin-rights',
  templateUrl: './admin-rights.component.html',
  styleUrls: ['./admin-rights.component.css']
})
export class AdminRightsComponent implements OnInit {
    data: AdminRightsDto[] = [];
    rightTypes: string[] = [];
  constructor(private service: AdminService) { }

  ngOnInit() {
        this.service.getAllRights().subscribe(
            res => {
                this.data = res;
            },
            err => {console.log(err);}
        );
        this.service.getAllRightTypes().subscribe(
            res => {
                this.rightTypes = res;
            },
            err => {console.log(err);}
        );
  }

    sendData():void{
        this.service.setAllRights(this.data);
    }

    hasRight(dto:AdminRightsDto, right:string): boolean{
        return (dto.rights.indexOf(right) != -1);
    }

    changeRightStatus(dto:AdminRightsDto, right:string):void{
        if(dto.rights.indexOf(right) != -1){
            dto.rights.splice(dto.rights.indexOf(right),1);
        }
        else{
            dto.rights.push(right);
        }
    }
}
