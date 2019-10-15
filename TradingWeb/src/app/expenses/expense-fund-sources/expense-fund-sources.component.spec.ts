import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseFundSourcesComponent } from './expense-fund-sources.component';

describe('ExpenseFundSourcesComponent', () => {
  let component: ExpenseFundSourcesComponent;
  let fixture: ComponentFixture<ExpenseFundSourcesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpenseFundSourcesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpenseFundSourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
