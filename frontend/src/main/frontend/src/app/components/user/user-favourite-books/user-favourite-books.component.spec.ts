import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserFavouriteBooksComponent } from './user-favourite-books.component';

describe('UserFavouriteBooksComponent', () => {
  let component: UserFavouriteBooksComponent;
  let fixture: ComponentFixture<UserFavouriteBooksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserFavouriteBooksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserFavouriteBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
