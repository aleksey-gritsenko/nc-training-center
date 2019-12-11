import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../models/book";
import {DomSanitizer} from "@angular/platform-browser";
import {CommonService} from "../../services/common/common.service";

@Component({
    selector: 'app-book-img',
    templateUrl: './book-img.component.html',
    styleUrls: ['./book-img.component.css']
})
export class BookImgComponent implements OnInit {

    @Input() book: Book;

    constructor(private apiService: CommonService,
                private sanitizer: DomSanitizer) {
    }

    ngOnInit() {
        if (this.book != null) {
            this.getImgByBook();
        }
    }

    getImgByBook() {
        this.apiService.getImageByBook(this.book).subscribe(
            res => {
                let reader = new FileReader();
                reader.addEventListener("load", () => {
                    this.book.photoURL = reader.result;
                }, false);
                if (res) {
                    reader.readAsDataURL(res);
                }
            }
        );
    }

    getSantizeUrl(url: string) {
        return this.sanitizer.bypassSecurityTrustUrl(url);
    }
}
