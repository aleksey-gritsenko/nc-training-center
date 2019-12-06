import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AddAchievmentComponent} from './add-achievment.component';

describe('AddAchievmentComponent', () => {
  let component: AddAchievmentComponent;
  let fixture: ComponentFixture<AddAchievmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAchievmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAchievmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
