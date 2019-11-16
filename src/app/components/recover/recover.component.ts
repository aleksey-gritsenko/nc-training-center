import {Component, OnInit} from '@angular/core';
import {RecView} from '../../models/recview';
import {HttpClient, HttpRequest} from '@angular/common/http';

@Component({
  selector: 'app-recover',
  templateUrl: './recover.component.html',
  styleUrls: ['./recover.component.css']
})
export class RecoverComponent implements OnInit {
  model: RecView = {
    login: '',
    email: ''
  };

  constructor(private http: HttpClient) {

  }

  ngOnInit() {
  }

  recover(): void {
    const url = 'http://localhost:8080/userrrecover';
    const form = new FormData();
    form.append('email', this.model.email);
    this.http.post(url, form).subscribe();
    alert(this.model.email);
  }

}
