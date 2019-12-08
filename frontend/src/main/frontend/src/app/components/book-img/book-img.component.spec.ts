import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookImgComponent } from './book-img.component';

describe('BookImgComponent', () => {
  let component: BookImgComponent;
  let fixture: ComponentFixture<BookImgComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookImgComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookImgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
