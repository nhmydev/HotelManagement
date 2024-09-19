import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../Services/admin.service';

@Component({
  selector: 'app-updateroom',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './updateroom.component.html',
  styleUrl: './updateroom.component.css',
})
export class UpdateroomComponent {
  updateRoomForm: FormGroup;
  id: any;
  constructor(
    private fb: FormBuilder,
    private adminService: AdminService,
    private router: Router,
    private activedRoute: ActivatedRoute
  ) {
    this.id = this.activedRoute.snapshot.params['id'];
    this.updateRoomForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      price: ['', Validators.required],
      available: ['', Validators.required],
    });
    this.getRoomById();
    this.updateRoomForm
      .get('available')
      .valueChanges.subscribe((value: boolean) => {
        console.log('Available status changed:', value);
      });
  }

  submit() {
    this.adminService
      .updateRoom(this.id, this.updateRoomForm.value)
      .subscribe((res) => {
        this.router.navigateByUrl('/admin/dashboard');
      });
  }
  getRoomById() {
    this.adminService.getRoomByID(this.id).subscribe(
      (res) => {
        res.available = res.available ? true : false;
        this.updateRoomForm.patchValue(res);
      },
      (error) => {
        console.log('Khong tim thay');
      }
    );
  }
}
