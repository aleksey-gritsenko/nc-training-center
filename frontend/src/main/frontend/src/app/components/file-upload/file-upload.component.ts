import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Book} from "../../models/book";
import * as FileSaver from 'file-saver';
import {DomSanitizer} from "@angular/platform-browser";
import {StorageService} from "../../services/storage/storage.service";
import {User} from "../../models/user";


@Component({
    selector: 'app-file-upload',
    templateUrl: './file-upload.component.html',
    styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {
    form: FormGroup;

    @Input() book: Book;
    fileUploadVisible: boolean = false;
    fileExtensions = ['.txt', '.pdf'];
    imgExtensions = ['.png'];
    fileToUpload: File;
    receivedImageData: any;
    base64Data: any;
    convertedImage: any;
    image: any;
    private siteUrl: string = 'https://nc-group1-2019.herokuapp.com';

    constructor(private http: HttpClient, private sanitizer: DomSanitizer, private storage: StorageService) {
    }

    ngOnInit() {
        let user:User = this.storage.getUser();
        if(user!=null){
            if (user.userRole == 'moderator') {
                this.fileUploadVisible = true;
            }
        }
    }

    // private localhost: string = 'http://localhost:8080';

    postFile(event) {
        this.fileToUpload = event.target.files[0];
        let formData = new FormData();
        let name = this.fileToUpload.name;
        let index = name.lastIndexOf(".");
        let extensions = name.substring(index, name.length);
        if (this.imgExtensions.indexOf(extensions) != -1) {
            const url = `${this.siteUrl}` + `/book/addImage`;
            formData.append('img', this.fileToUpload);
            formData.append('bookId', this.book.id.toString());
            this.http.post(url + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, formData).subscribe(res => {
                    console.log(res);
                    this.receivedImageData = res;
                    this.base64Data = this.receivedImageData.pic;
                    this.convertedImage = 'data:image/png;base64,' + this.base64Data;
                },
                err => console.log('Error Occured during saving: ' + err)
            );

        }
        if (this.fileExtensions.indexOf(extensions) != -1) {
            const url = `${this.siteUrl}` + `/book/addFile`;
            formData.append('file', this.fileToUpload);
            formData.append('bookId', this.book.id.toString());
            this.http.post(url + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, formData).subscribe((file) => {
                console.log(file);
            });
        }
    }

    downloadPDF(): any {
        const url = `${this.siteUrl}` + `/book/bookFile`;
        return this.http.post(url, this.book, {responseType: 'blob' as 'text'}).subscribe(
            (res) => {
                console.log(res);
                let blob = new Blob([res], {type: 'application/pdf'});
                this.book.fileURL = URL.createObjectURL(blob);
                let file = new File([blob], this.book.header, {type: blob.type});
                FileSaver.saveAs(file);
            }
        );
    }
}
