import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPlazaComponent } from './search-plaza.component';

describe('SearchPlazaComponent', () => {
  let component: SearchPlazaComponent;
  let fixture: ComponentFixture<SearchPlazaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchPlazaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SearchPlazaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
