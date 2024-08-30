import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { RegisterComponent } from './auth/components/register/register.component';
import { LoginComponent } from './auth/components/login/login.component';
import { RoomsComponent } from './modules/customer/components/rooms/rooms.component';
import { DashboardComponent } from './modules/admin/components/dashboard/dashboard.component';
import { PostroomComponent } from './modules/admin/components/postroom/postroom.component';
import { UpdateroomComponent } from './modules/admin/components/updateroom/updateroom.component';

export const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'customer',
    children: [{ path: 'rooms', component: RoomsComponent }],
  },
  {
    path: 'admin',
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'room', component: PostroomComponent },
      { path: 'room/:id/edit', component: UpdateroomComponent },
    ],
  },
];
