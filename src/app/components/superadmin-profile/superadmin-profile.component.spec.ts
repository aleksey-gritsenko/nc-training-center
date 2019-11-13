import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuperadminProfileComponent } from './superadmin-profile.component';

describe('SuperadminProfileComponent', () => {
  let component: SuperadminProfileComponent;
  let fixture: ComponentFixture<SuperadminProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuperadminProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperadminProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
