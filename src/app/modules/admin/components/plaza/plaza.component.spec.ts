import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlazaComponent } from './plaza.component';

describe('PlazaComponent', () => {
  let component: PlazaComponent;
  let fixture: ComponentFixture<PlazaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlazaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PlazaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
