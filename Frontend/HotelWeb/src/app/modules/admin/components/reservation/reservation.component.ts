import { Component } from '@angular/core';
import { AdminService } from '../../Services/admin.service';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-reservation',
  standalone: true,
  imports: [NgFor, NgIf],
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css',
})
export class ReservationComponent {
  currentPage: any = 1;
  total: any;
  reservations: any;
  totalPage: any;
  constructor(private adminService: AdminService) {
    this.getReversation();
  }
  getReversation() {
    this.adminService.getReversation(this.currentPage - 1).subscribe(
      (res) => {
        this.reservations = res.reservationDtoList;
        this.total = res.totalPages * 4;
        this.totalPage = res.totalPages;
      },
      (error) => {}
    );
  }
  pageIndexChange(value: number) {
    if (value < 1 || value > this.totalPage) {
      return;
    }
    this.currentPage = value;
    this.getReversation();
  }
  changeStatus(id: number, status: String) {
    this.adminService.changStatus(id, status).subscribe(
      (res) => {
        this.getReversation();
      },
      (error) => {}
    );
  }
}
