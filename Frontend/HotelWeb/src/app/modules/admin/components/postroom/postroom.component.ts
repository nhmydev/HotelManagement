import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AdminService } from '../../Services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-postroom',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './postroom.component.html',
  styleUrl: './postroom.component.css',
})
export class PostroomComponent {
  roomDetailForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private adminService: AdminService,
    private router: Router
  ) {
    this.roomDetailForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      price: ['', Validators.required],
    });
  }
  submit() {
    this.adminService.postRoomDetails(this.roomDetailForm.value).subscribe(
      (res) => {
        this.router.navigateByUrl('/admin/dashboard');
      },
      (error) => {
        console.log('Error');
      }
    );
  }
}
