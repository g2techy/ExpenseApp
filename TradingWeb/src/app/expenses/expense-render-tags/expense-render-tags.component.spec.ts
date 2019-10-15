import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseRenderTagsComponent } from './expense-render-tags.component';

describe('ExpenseRenderTagsComponent', () => {
  let component: ExpenseRenderTagsComponent;
  let fixture: ComponentFixture<ExpenseRenderTagsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpenseRenderTagsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpenseRenderTagsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
