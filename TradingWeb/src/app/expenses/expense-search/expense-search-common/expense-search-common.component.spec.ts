import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseSearchCommonComponent } from './expense-search-common.component';

describe('ExpenseSearchCommonComponent', () => {
  let component: ExpenseSearchCommonComponent;
  let fixture: ComponentFixture<ExpenseSearchCommonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpenseSearchCommonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpenseSearchCommonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
