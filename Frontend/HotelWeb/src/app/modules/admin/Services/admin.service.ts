import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../../auth/services/storage/user-storage.service';

const BASE_URL = 'http://localhost:8080/api/admin/';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private http: HttpClient) {}

  postRoomDetails(roomDTO: any): Observable<any> {
    return this.http.post(BASE_URL + 'room', roomDTO, {
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
  getRooms(pageNumber: number): Observable<any> {
    return this.http.get(BASE_URL + `rooms/${pageNumber}`, {
      headers: this.createAuthorHeader(),
    });
  }
  getRoomByID(id: number): Observable<any> {
    return this.http.get(BASE_URL + `room/${id}`, {
      headers: this.createAuthorHeader(),
    });
  }
  getReversation(pageNumber: number): Observable<any> {
    return this.http.get(BASE_URL + `reservation/${pageNumber}`, {
      headers: this.createAuthorHeader(),
    });
  }
  updateRoom(id: number, roomDTO: any): Observable<any> {
    return this.http.put(BASE_URL + `room/${id}`, roomDTO, {
      headers: this.createAuthorHeader(),
    });
  }
  deleteRoom(id: number): Observable<any> {
    return this.http.delete(BASE_URL + `room/${id}`, {
      headers: this.createAuthorHeader(),
    });
  }
  changStatus(id: number, status: String): Observable<any> {
    return this.http.get(BASE_URL + `reservation/${id}/${status}`, {
      headers: this.createAuthorHeader(),
    });
  }
}
