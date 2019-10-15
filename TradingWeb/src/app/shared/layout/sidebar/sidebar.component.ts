import { Component, OnInit, OnDestroy, ViewChild, QueryList } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { Router } from '@angular/router';
import { SharedService } from '../../shared.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit, OnDestroy {

  private toggleSidebar$ : Subscription = null;
  toggledClassName : string = '';
  @ViewChild('navbar', null) navbar: any;

  constructor(private sharedService : SharedService, private router: Router) { }

  ngOnInit() {
    this.toggleSidebar$ = this.sharedService.toggleSidebar$.subscribe(data => {
      this.toggledClassName = (this.toggledClassName == ''? 'toggled': '')
    });
  }

  ngOnDestroy() {
    if (this.toggleSidebar$) {
      this.toggleSidebar$.unsubscribe();
    }
  }

  onNavItemClick(elem: any, url: string) {
    this.removeActiveNavItem();
    elem.classList.add('active');
    this.router.navigateByUrl(url);
  }

  private removeActiveNavItem() {
    if (this.navbar) {
      const navItems = this.navbar.nativeElement.getElementsByTagName("li");
      if (navItems) {
        for (var item of navItems) {
          item.classList.remove('active');
        }
      }
    }
  }

}
