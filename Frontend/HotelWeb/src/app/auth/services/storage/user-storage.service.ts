import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root',
})
export class UserStorageService {
  constructor() {}
  static saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }
  static saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string {
    return localStorage.getItem(TOKEN);
  }
  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }
  static getUserId(): string {
    const user = this.getUser();
    if (user == null) return '';
    return user.id;
  }

  static getUserRole(): string {
    const user = this.getUser();
    if (user == null) return '';
    return user.role;
  }

  static isAdmin(): boolean {
    if (this.getUserRole() === null) return false;
    const role = this.getUserRole();
    return role == 'ADMIN';
  }

  static isUser(): boolean {
    if (this.getUserRole() === null) return false;
    const role = this.getUserRole();
    return role == 'CUSTOMER';
  }

  static logOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
