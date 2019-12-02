import {Component, OnInit} from '@angular/core';
import {RecView} from '../../../models/recview';
import {RecoverService} from '../../../services/recover/recover.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-recover',
    templateUrl: './recover.component.html',
    styleUrls: ['../../../resources/styles/Authorization.css']
})
export class RecoverComponent implements OnInit {
    model: RecView = {
        id: 0,
        login: '',
        email: ''
    };

    constructor(private recoverService: RecoverService, private router: Router,
                private route: ActivatedRoute) {

    }

    ngOnInit() {

    }


    recover(): void {
        this.recoverService.sendRecoverCode(this.model.email);
        this.router.navigateByUrl('/change')
    }
}
