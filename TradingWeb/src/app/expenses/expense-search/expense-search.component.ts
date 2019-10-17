import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { map, filter, take, mergeMap } from 'rxjs/operators';

@Component({
  selector: 'app-expense-search',
  templateUrl: './expense-search.component.html',
  styleUrls: ['./expense-search.component.scss']
})
export class ExpenseSearchComponent implements OnInit {

  selectedTab: string = 'cm';  

  constructor(private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.subscribeNavigationEnd();
  }

  subscribeNavigationEnd() {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => {
        let route = this.activatedRoute.firstChild;
        let child = route;

        while (child) {
            if (child.firstChild) {
                child = child.firstChild;
                route = child;
            } else {
                child = null;
            }
        }

        return route;
      }),
      mergeMap(route => route.data)
    ).subscribe(data => {      
      this.showSelectedTab(data);
    });
  }

  showSelectedTab(data) {
    this.selectedTab = data['searchType'] || 'cm';
  }

}
