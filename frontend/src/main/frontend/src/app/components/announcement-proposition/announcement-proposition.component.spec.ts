import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AnnouncementPropositionComponent} from './announcement-proposition.component';

describe('AnnouncementPropositionComponent', () => {
    let component: AnnouncementPropositionComponent;
    let fixture: ComponentFixture<AnnouncementPropositionComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [AnnouncementPropositionComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(AnnouncementPropositionComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
