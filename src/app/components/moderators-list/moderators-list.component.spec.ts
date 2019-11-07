import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModeratorsListComponent } from './moderators-list.component';

describe('ModeratorsListComponent', () => {
  let component: ModeratorsListComponent;
  let fixture: ComponentFixture<ModeratorsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModeratorsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModeratorsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
