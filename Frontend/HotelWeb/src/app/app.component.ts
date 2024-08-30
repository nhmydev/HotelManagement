import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  RouterOutlet,
} from '@angular/router';
import { UserStorageService } from './auth/services/storage/user-storage.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    ReactiveFormsModule,
    RouterLink,
    RouterLinkActive,
    NgIf,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'HotelManament';

  isCustomer: boolean = UserStorageService.isUser();
  isAdmin: boolean = UserStorageService.isAdmin();

  constructor(private router: Router) {}
  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event.constructor.name === 'NavigationEnd') {
        this.isCustomer = UserStorageService.isUser();
        this.isAdmin = UserStorageService.isAdmin();
      }
    });
  }
  logout() {
    UserStorageService.logOut();
    this.router.navigateByUrl('/login');
  }
}
