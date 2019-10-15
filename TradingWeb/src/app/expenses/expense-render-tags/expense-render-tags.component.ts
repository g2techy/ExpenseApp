import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-expense-render-tags',
  templateUrl: './expense-render-tags.component.html',
  styleUrls: ['./expense-render-tags.component.scss']
})
export class ExpenseRenderTagsComponent implements OnInit, OnChanges {

  @Input() tags: any;
  tagList = [];

  constructor() { }

  ngOnInit() {

  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes){
      if(changes['tags']){
        this.tagList = this.tags.split(';').map(t => t.trim()).filter(t => t.length > 0);
      }
    }
  }


}
