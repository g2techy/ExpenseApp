import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TradeSearchComponent } from './trade-search.component';

describe('TrageSearchComponent', () => {
  let component: TradeSearchComponent;
  let fixture: ComponentFixture<TradeSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TradeSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TradeSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
