import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../services/storage/storage.service";

@Component({
    selector: 'app-landing',
    templateUrl: './landing.component.html',
    styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

    constructor(private storageService: StorageService) {
    }

    ngOnInit() {
    }

}
