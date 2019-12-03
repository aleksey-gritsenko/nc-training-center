import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LogView} from "../../models/logview";
import {User} from "../../models/user";
import {CookieService} from "ngx-cookie-service";

@Component({
    selector: 'app-testcomponent',
    templateUrl: './testcomponent.component.html',
    styleUrls: ['./testcomponent.component.css']
})
export class TestcomponentComponent implements OnInit {
    model: LogView = {
        id: 0,
        login: '',
        password: ''
    };

    constructor(private http: HttpClient, private cookieServise: CookieService) {
    }

    ngOnInit() {
        // const url = 'http://localhost:8080/test';
        // this.http.get(url).subscribe(
        //     res => {
        //         alert(res);
        //         if (res != null) {
        //             alert('good');
        //         }
        //     },
        //     err => {
        //         alert(err);
        //     }
        // );
        // alert('lolkek');
        this.tests();
        //   this.testGet();
        //   const url = 'http://localhost:8080/lol';
        //   const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('user:secret123') });
        //   return this.http.get(url, { headers });
        //   this.testSecurity();
    }

    tests(): void {
        let url = 'http://localhost:8080/test';
        let form = new FormData();
        form.append('login', 'Dmytro');
        const headers = new HttpHeaders({Authorization: 'Basic ' + btoa('Dmytro:dfg')});
        // const options = {headers: headers};
        form.append('password', 'dfg');
        this.http.post<User>(url, form, {headers}).subscribe(
            res => {
                if (res != null) {
                    alert('Success auth!!!');
                }
            },
            err => {
                alert(JSON.parse(JSON.stringify(err)).message);
            }
        );

        // const headers = new HttpHeaders({Authorization: 'Basic ' + btoa('Dmytro:dfg')});
        // this.http.post<User>(url2, form, {headers}).subscribe(
        //     res => {
        //         if (res != null) {
        //             alert('Success secure entrance');
        //         }
        //     },
        //     err => {
        //         alert(JSON.parse(JSON.stringify(err)).message);
        //     }
        // );
    }

    testGet(): void {
        const url = 'http://localhost:8080/testget';
        const headers = new HttpHeaders({Authorization: 'Basic ' + btoa('Dmytro:dfg')});
        this.http.get(url, {headers}).subscribe(
            res => {
                if (res != null) {
                    alert('Success auth in the get contrller!!!');
                }
            },
            err => {
                alert(JSON.parse(JSON.stringify(err)).message);
            }
        );

    }

    testSecurity(): void {
        const url = 'http://localhost:8080/lol';
        let form = new FormData();
        form.append('login', 'Dmytro');
        const headers = new HttpHeaders({Authorization: 'Basic ' + btoa('Dmytro:dfg')});
        form.append('password', 'dfg');
        this.http.post<User>(url, {headers}).subscribe(
            res => {
                if (res != null) {
                    alert('Success secure entrance');
                }
            },
            err => {
                alert(JSON.parse(JSON.stringify(err)).message);
            }
        );
    }
}
