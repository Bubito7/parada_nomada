import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookPlazaComponent } from './book-plaza.component';

describe('BookPlazaComponent', () => {
  let component: BookPlazaComponent;
  let fixture: ComponentFixture<BookPlazaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookPlazaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookPlazaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
