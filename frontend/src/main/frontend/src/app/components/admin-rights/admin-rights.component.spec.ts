import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AdminRightsComponent} from './admin-rights.component';

describe('AdminRightsComponent', () => {
    let component: AdminRightsComponent;
    let fixture: ComponentFixture<AdminRightsComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [AdminRightsComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(AdminRightsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
