import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-mybooking',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './mybooking.component.html',
  styleUrl: './mybooking.component.css',
})
export class MybookingComponent {
  currentPage: any = 1;
  bookingList = [];
  totalPage: any;
  constructor(private customerService: CustomerService) {
    this.getMyBooking();
  }

  getMyBooking() {
    this.customerService.getMyBooking(this.currentPage - 1).subscribe(
      (res) => {
        this.bookingList = res.reservationDtoList;
        this.totalPage = res.totalPages;
        console.log(this.bookingList);
      },
      (error) => {}
    );
  }
}
