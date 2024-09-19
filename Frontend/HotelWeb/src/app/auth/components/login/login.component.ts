import { UserStorageService } from './../../services/storage/user-storage.service';
import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginForm!: FormGroup;
  loginError: string = '';
  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder
  ) {}
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.email, Validators.required]],
      password: ['', Validators.required],
    });
  }
  submitForm() {
    this.authService.login(this.loginForm.value).subscribe(
      (res) => {
        if (res.userid != null) {
          const user = {
            id: res.userid,
            role: res.userRole,
          };
          UserStorageService.saveUser(user);
          UserStorageService.saveToken(res.jwt);
          if (UserStorageService.isAdmin()) {
            this.router.navigateByUrl('/admin/dashboard');
          } else if (UserStorageService.isUser()) {
            this.router.navigateByUrl('/customer/rooms');
          }
        }
      },
      (error) => {
        this.loginError =
          'Login failed. Please check your username or password and try again.';
      }
    );
  }
}
