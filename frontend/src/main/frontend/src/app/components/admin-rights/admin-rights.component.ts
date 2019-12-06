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
  constructor(private service: AdminService) { }

  ngOnInit() {
        this.service.getAllRights().subscribe(
            res => {
                for(var el of res){
                    this.data.push(el);
                }
            },
            err => {console.log(err);}
        );
  }

    sendData():void{
        this.service.setAllRights(this.data);
    }
}
