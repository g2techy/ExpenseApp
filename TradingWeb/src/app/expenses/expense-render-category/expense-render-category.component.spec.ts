import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseRenderCategoryComponent } from './expense-render-category.component';

describe('ExpenseRenderCategoryComponent', () => {
  let component: ExpenseRenderCategoryComponent;
  let fixture: ComponentFixture<ExpenseRenderCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpenseRenderCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpenseRenderCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
