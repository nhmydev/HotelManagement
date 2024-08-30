import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../../auth/services/storage/user-storage.service';

const BASE_URL = 'http://localhost:8080/api/customer/';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private http: HttpClient) {}

  getRooms(pageNumber: number): Observable<any> {
    return this.http.get(BASE_URL + `rooms/${pageNumber}`, {
      headers: this.createAuthorHeader(),
    });
  }
  createAuthorHeader() {
    let authHeader: HttpHeaders = new HttpHeaders();
    return authHeader.set(
      'Authorization',
      'Bearer ' + UserStorageService.getToken()
    );
  }
}
