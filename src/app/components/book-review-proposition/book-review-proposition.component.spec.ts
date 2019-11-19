import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BookReviewPropositionComponent} from './book-review-proposition.component';

describe('BookReviewPropositionComponent', () => {
    let component: BookReviewPropositionComponent;
    let fixture: ComponentFixture<BookReviewPropositionComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [BookReviewPropositionComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(BookReviewPropositionComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
