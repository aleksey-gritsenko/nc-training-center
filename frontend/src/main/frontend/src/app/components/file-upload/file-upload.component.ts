import {Component, Input, OnInit,Directive} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Book} from "../../models/book";
import * as FileSaver from 'file-saver';


@Component({
    selector: 'app-file-upload',
    templateUrl: './file-upload.component.html',
    styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements  OnInit{
    form: FormGroup;

    @Input() book:Book;
    constructor(private http: HttpClient) {
    }
    ngOnInit(){
    }
    fileExtensions = ['.txt'];
    imgExtensions = ['.png'];
    fileToUpload:File;

    fileURL:any;
    receivedImageData: any;
    base64Data: any;
    convertedImage: any;
    image:any;

    postFile(event) {
        this.fileToUpload = event.target.files[0];
        let formData = new FormData();
        let name = this.fileToUpload.name;
        let index = name.lastIndexOf(".");
        let strsubstring = name.substring(index, name.length);
        if(this.imgExtensions.indexOf(strsubstring)!=-1)
        {
            formData.append('img', this.fileToUpload);
            formData.append('bookId', this.book.id.toString());
            this.http.post(`http://localhost:8080/book/addImage`,formData).subscribe(res => {console.log(res);
                    this.receivedImageData = res;
                    this.base64Data = this.receivedImageData.pic;
                    this.convertedImage = 'data:image/png;base64,' + this.base64Data;},
                err => console.log('Error Occured during saving: ' + err)
            );

        }
        if(this.fileExtensions.indexOf(strsubstring)!=-1)
        {
            formData.append('file', this.fileToUpload);
            formData.append('bookId', this.book.id.toString());
            this.http.post(`http://localhost:8080/book/addFile`,formData).subscribe((file) => {
                console.log(file);
            });
        }
    }
    downloadPDF(): any {
        return this.http.post(`http://localhost:8080/book/bookFile`,this.book, {responseType:'blob'}).subscribe(
            (res) => {
                this.createFile(res);
                let reader = new FileReader();
                this.fileURL = reader.result;
            }
        );
    }

    createFile(file:Blob){
        let reader = new FileReader();
        reader.addEventListener("load", () => {
            this.fileURL = reader.result;
            console.log(this.fileURL);
        }, false);
        if (file) {
            FileSaver.saveAs(file);
        }
    }

}
