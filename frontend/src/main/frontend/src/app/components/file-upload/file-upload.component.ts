import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Review} from "../../models/review";
import {Book} from "../../models/book";

@Component({
    selector: 'app-file-upload',
    templateUrl: './file-upload.component.html',
    styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent {
    form: FormGroup;

    @Input() book:Book;
    constructor(private http: HttpClient) {
    }
    fileExtensions = ['.txt'];
    imgExtensions = ['.png'];

    fileToUpload:File;
    postImg(files: FileList) {
        this.fileToUpload = files.item(0);
        let formData = new FormData();
        let name = this.fileToUpload.name;
        let index = name.lastIndexOf(".");
        let strsubstring = name.substring(index, name.length);
        if(this.imgExtensions.indexOf(strsubstring)!=-1)
        {
            console.log(this.fileToUpload);
            formData.append('file', this.fileToUpload);
            formData.append('bookId', this.book.id.toString());
            this.http.post(`http://localhost:8080/book/addImage`,formData).subscribe((file) => {
                console.log(file);
            });
        }
        if(this.fileExtensions.indexOf(strsubstring)!=-1)
        {
            console.log(this.fileToUpload);
            formData.append('img', this.fileToUpload);
            formData.append('bookId', this.book.id.toString());
            this.http.post(`http://localhost:8080/book/addFile`,formData).subscribe((file) => {
                console.log(file);
            });
        }
        return false;
    }


}
