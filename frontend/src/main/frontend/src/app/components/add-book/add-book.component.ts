import { Component, OnInit } from '@angular/core';
import {Book} from "../../models/book";
import {Author} from "../../models/author";
import {CommonService} from "../../services/common/common.service";
import {FormBuilder, FormGroup, FormArray, Validators, FormControl} from '@angular/forms';
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../services/storage/storage.service";

@Component({
    selector: 'app-add-book',
    templateUrl: './add-book.component.html',
    styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
    model:Book  = new Book();
    createdBook: FormGroup;

    constructor(private apiService: CommonService,
                private route: ActivatedRoute,
                private router: Router,
                private formBuilder: FormBuilder) {
    }


    get authors():FormArray {
        return this.createdBook.get("authors") as FormArray
    }

    newAuthor(): FormGroup {
        return this.formBuilder.group({
            author:'',
        })
    }
    addAuthor() {
        this.authors.push(this.newAuthor());
    }
    removeAuthor(i:number) {
        this.authors.removeAt(i);
    }
    onSubmit() {
        console.log(this.authors.value);
    }

    ngOnInit() {
        this.createdBook = this.formBuilder.group({
            header: ['',Validators.required],
            overview: ['',Validators.required],
            status: ['',Validators.required],
            genre: ['',Validators.required],
            authors:  this.formBuilder.array([])
        });
    }

    createBook(): void {
        const book = this.createdBook.controls;
        console.log(this.createdBook);

        this.model.header = book.header.value;
        this.model.overview = book.overview.value;
        this.model.genre = book.genre.value;
        this.model.status = book.status.value;
        this.model.authors = book.authors.value;

        console.log(this.model);


        this.apiService.createBook(this.model)
            .subscribe(res => {
                   // console.log(newCreatedBook);
                },
                err => {
                    this.router.navigateByUrl('/error');
                });

    }

}
