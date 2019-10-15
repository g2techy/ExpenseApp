import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseTagListComponent } from './expeonse-tag-list.component';

describe('ExpenseTagListComponent', () => {
  let component: ExpenseTagListComponent;
  let fixture: ComponentFixture<ExpenseTagListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpenseTagListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpenseTagListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
