import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePlazaComponent } from './update-plaza.component';

describe('UpdatePlazaComponent', () => {
  let component: UpdatePlazaComponent;
  let fixture: ComponentFixture<UpdatePlazaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdatePlazaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdatePlazaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
