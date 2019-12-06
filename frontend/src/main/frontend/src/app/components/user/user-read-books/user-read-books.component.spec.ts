import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserReadBooksComponent } from './user-read-books.component';

describe('UserReadBooksComponent', () => {
  let component: UserReadBooksComponent;
  let fixture: ComponentFixture<UserReadBooksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserReadBooksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserReadBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
