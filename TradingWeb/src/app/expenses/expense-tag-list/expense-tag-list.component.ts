import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-expense-tag-list',
  templateUrl: './expense-tag-list.component.html',
  styleUrls: ['./expense-tag-list.component.scss']
})
export class ExpenseTagListComponent implements OnInit, OnChanges {

  @Input() tags: any;
  @Input() selectedTags: any;

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes) {
      if(changes['selectedTags']){
        this.setSelectedTags();
      }
    }
  }

  getSelectedTags() : any {
    let selTags = [];
    this.tags.forEach(item => {
      if(!!item.selected){
        selTags.push(item);
      }
    });
    return selTags;
  }

  setSelectedTags() {
    let selTags = this.selectedTags;
    this.resetAllTags();
    if(selTags != null && selTags.length > 0){
      selTags.forEach(item => {
        let tags = this.tags.filter(t => t.typeValueId == item.tagId);
        if(tags && tags.length > 0){
          tags[0].selected = true;
        }
      });
    }
  }

  private resetAllTags(){
    if(this.tags != null && this.tags.length > 0){
      this.tags.forEach(item => { item.selected = false; });
    }
  }

}
