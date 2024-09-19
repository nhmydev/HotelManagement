import { UserStorageService } from './../../../../auth/services/storage/user-storage.service';
import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-rooms',
  standalone: true,
  imports: [NgFor, NzPaginationModule, RouterLink, NgIf, NgClass, FormsModule],
  templateUrl: './rooms.component.html',
  styleUrl: './rooms.component.css',
})
export class RoomsComponent {
  showModal = false;
  currentPage = 1;
  room = [];
  total: any;
  totalPage;
  selectedRoom: any = null;

  constructor(private customerService: CustomerService) {
    this.getRooms();
  }
  getRooms() {
    this.customerService.getRooms(this.currentPage - 1).subscribe((res) => {
      this.room = res.roomDtos;
      this.totalPage = res.totalPage;
      this.total = res.totalPage * 1;
    });
  }
  pageIndexChange(value: any) {
    if (value < 1 || value > this.totalPage) {
      return;
    }
    this.currentPage = value;
    this.getRooms();
  }
  checInDateString: string = '';
  checOutDateString: string = '';
  checkInDate: Date;
  checkOutDate: Date;
  id: number;

  openModal(id: number) {
    this.id = id;
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }
  onChangecheckInDate(): void {
    if (this.checInDateString) {
      this.checkInDate = new Date(this.checInDateString);
      console.log('Check-in Date:', this.checkInDate);
    } else {
      console.log('Check-in Date is invalid');
    }
  }

  onChangecheckOutDate(): void {
    this.checkOutDate = new Date(this.checOutDateString);
    console.log('Check-out Date:', this.checkOutDate);
  }

  confirmBooking(): void {
    const obj = {
      userId: UserStorageService.getUserId(),
      roomId: this.id,
      checkInDate: this.checkInDate,
      checkOutDate: this.checkOutDate,
    };
    console.log(obj);
    this.customerService.bookRoom(obj).subscribe(
      (res) => {
        alert('thanh cong');
      },
      (error) => {
        alert('that bai');
      }
    );
    this.closeModal();
  }
}
