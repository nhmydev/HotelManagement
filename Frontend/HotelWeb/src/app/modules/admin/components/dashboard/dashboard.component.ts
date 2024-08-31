import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AdminService } from '../../Services/admin.service';
import { NgFor, NgIf } from '@angular/common';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NgFor, NzPaginationModule, RouterLink, NgIf],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  showModal = false;
  currentPage = 1;
  room = [];
  total: any;
  totalPage;
  selectedRoomId: number | null = null;
  constructor(private adminService: AdminService) {
    this.getRooms();
  }
  getRooms() {
    this.adminService.getRooms(this.currentPage - 1).subscribe((res) => {
      this.totalPage = res.totalPage;
      this.room = res.roomDtos;
      this.total = res.totalPage * 1;
    });
  }
  pageIndexChange(value: any) {
    this.currentPage = value;
    this.getRooms();
  }
  showConfirm(id: number) {
    this.selectedRoomId = id;
    this.showModal = true;
  }

  confirmDelete() {
    if (this.selectedRoomId !== null) {
      this.adminService.deleteRoom(this.selectedRoomId).subscribe(
        (res) => {
          console.log('Xóa thành công');
          this.getRooms();
          this.cancelDelete();
        },
        (error) => {
          console.log('Xóa thất bại');
        }
      );
    }
  }
  cancelDelete() {
    this.showModal = false;
    this.selectedRoomId = null;
  }
}
