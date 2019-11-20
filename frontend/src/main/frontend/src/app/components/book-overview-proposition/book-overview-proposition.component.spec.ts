import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BookOverviewPropositionComponent} from './book-overview-proposition.component';

describe('BookOverviewPropositionComponent', () => {
    let component: BookOverviewPropositionComponent;
    let fixture: ComponentFixture<BookOverviewPropositionComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [BookOverviewPropositionComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(BookOverviewPropositionComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
