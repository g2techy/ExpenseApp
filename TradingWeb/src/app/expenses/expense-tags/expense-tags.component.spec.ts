import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseTagsComponent } from './expense-tags.component';

describe('ExpenseTagsComponent', () => {
  let component: ExpenseTagsComponent;
  let fixture: ComponentFixture<ExpenseTagsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpenseTagsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpenseTagsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
