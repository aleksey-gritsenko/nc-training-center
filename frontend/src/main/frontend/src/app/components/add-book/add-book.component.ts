import { Component, OnInit } from '@angular/core';
import {Book} from "../../models/book";
import {Author} from "../../models/author";
import {CommonService} from "../../services/common/common.service";
import {FormBuilder, FormGroup, FormArray, Validators, FormControl} from '@angular/forms';
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../services/storage/storage.service";
import {Genre} from "../../models/genre";
import {toInteger} from "@ng-bootstrap/ng-bootstrap/util/util";

@Component({
    selector: 'app-add-book',
    templateUrl: './add-book.component.html',
    styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
    model:Book  = new Book();
    genres: Genre[] = [];
    createdBook: FormGroup;
    regExp = new RegExp(/^[A-Za-z0-9_]+$/);

    constructor(private commonService: CommonService,
                private route: ActivatedRoute,
                private router: Router,
                private formBuilder: FormBuilder) {
    }
    getGenres(){
        this.commonService.getAllGenre().subscribe(
            genres => {
                this.genres = genres;
            });
    }
    get authors():FormArray {
        return this.createdBook.get("authors") as FormArray
    }

    newAuthor(): FormGroup {
        return this.formBuilder.group({
            author:['',Validators.pattern(this.regExp)]
        })
    }
    addAuthor() {
        this.authors.push(this.newAuthor());
    }
    removeAuthor(i:number) {
        this.authors.removeAt(i);
        if(i==0){
            this.authors.push(this.newAuthor());
        }
    }
    ngOnInit() {

        this.createdBook = this.formBuilder.group({
            header: ['',[Validators.required, Validators.pattern(this.regExp)]],
            overview: ['',[Validators.required,Validators.pattern(this.regExp)]],
            status: ['',[Validators.required,Validators.pattern(this.regExp)]],
            genre: ['',[Validators.required,Validators.pattern(this.regExp)]],
            authors:   this.formBuilder.array([])
        });
        this.authors.push(this.newAuthor());
        this.getGenres();
    }

    createBook(): void {
        const book = this.createdBook.controls;
        this.model.header = book.header.value;
        this.model.overview = book.overview.value;
        this.model.genre = book.genre.value;
        this.model.status = book.status.value;

        this.authors.getRawValue().forEach(author => {
            let newAuthors:Author = {id:0, name:author['author']};
            this.model.authors.push(newAuthors);
        });

        this.commonService.createBook(this.model)
            .subscribe(res => {
                    this.model.authors = [];
                },
                err => {
                    this.router.navigateByUrl('/error');
                });
    }

    back(){
        this.commonService.back();
    }

}
