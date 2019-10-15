import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TradeAddComponent } from './trade-add.component';

describe('TradeAddComponent', () => {
  let component: TradeAddComponent;
  let fixture: ComponentFixture<TradeAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TradeAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TradeAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
