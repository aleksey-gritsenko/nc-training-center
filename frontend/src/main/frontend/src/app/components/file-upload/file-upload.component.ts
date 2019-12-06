import {Component, Input, OnInit,Directive} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Book} from "../../models/book";
import {DomSanitizer} from "@angular/platform-browser";
import {URL} from "url";
import {Observable} from 'rxjs';
import * as FileSaver from 'file-saver';

@Component({
    selector: 'app-file-upload',
    templateUrl: './file-upload.component.html',
    styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements  OnInit{
    form: FormGroup;
    bookImg:URL;
    @Input() book:Book;
    constructor(private http: HttpClient, private sanitizer: DomSanitizer) {
    }
    ngOnInit(){
        this.getImageByBook();
    }
    fileExtensions = ['.pdf'];
    imgExtensions = ['.png'];
    fileToUpload:File;

    imgURL: any;
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
            console.log(this.fileToUpload);
            formData.append('img', this.fileToUpload);
            formData.append('bookId', this.book.id.toString());
            this.http.post(`http://localhost:8080/book/addImage`,formData).subscribe(res => {console.log(res);
                    this.receivedImageData = res;
                    this.base64Data = this.receivedImageData.pic;
                    this.convertedImage = 'data:image/png;base64,' + this.base64Data;},
                err => console.log('Error Occured duringng saving: ' + err)
            );

        }
        if(this.fileExtensions.indexOf(strsubstring)!=-1)
        {
            console.log(this.fileToUpload);
            formData.append('file', this.fileToUpload);
            formData.append('bookId', this.book.id.toString());
            this.http.post(`http://localhost:8080/book/addFile`,formData).subscribe((file) => {
                console.log(file);
            });
        }
    }

    getImageByBook(){
        this.book.id=6;
        return this.http.post(`http://localhost:8080/book/bookImage`, this.book,{responseType: 'blob'}).subscribe(
            res=>{
                this.createImage(res);
                let reader = new FileReader();
                this.imgURL = reader.result;
            }
        );
    }

    getSantizeUrl(url : string) {
        return this.sanitizer.bypassSecurityTrustUrl(url);
    }

    createImage(img:Blob){
        let reader = new FileReader();
        reader.addEventListener("load", () => {
            this.imgURL = reader.result;
            console.log(this.imgURL);
        }, false);
        if (img) {
            reader.readAsDataURL(img);
        }
    }
    getPdf():Observable<Blob>{
        return this.http.post(`http://localhost:8080/book/bookFile`,this.book, {responseType:'blob'});
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

    // download() {
    //     this.getPdf().subscribe(res => {
    //         let reader = new FileReader();
    //         reader.addEventListener("load", () => {
    //             this.fileURL = reader.result;
    //             console.log(this.fileURL);
    //         }, false);
    //         if (res) {
    //             reader.readAsDataURL(res);
    //         }
    //         const linkSource = this.fileURL;
    //         const downloadLink = document.createElement("a");
    //         const fileName = "sample.pdf";
    //
    //         downloadLink.href =  linkSource;
    //         downloadLink.download = fileName;
    //         downloadLink.click();
    //     }, error => {
    //         console.log(error);
    //     })
    // }

}
