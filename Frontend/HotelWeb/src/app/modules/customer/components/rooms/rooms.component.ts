import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { NgFor, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';

@Component({
  selector: 'app-rooms',
  standalone: true,
  imports: [NgFor, NzPaginationModule, RouterLink, NgIf],
  templateUrl: './rooms.component.html',
  styleUrl: './rooms.component.css',
})
export class RoomsComponent {
  showModal = false;
  currentPage = 1;
  room = [];
  total: any;
  selectedRoomId: number | null = null;
  constructor(private customerService: CustomerService) {
    this.getRooms();
  }
  getRooms() {
    this.customerService.getRooms(this.currentPage - 1).subscribe((res) => {
      this.room = res.roomDtos;
      this.total = res.totalPage * 1;
    });
  }
  pageIndexChange(value: any) {
    this.currentPage = value;
    this.getRooms();
  }
}
