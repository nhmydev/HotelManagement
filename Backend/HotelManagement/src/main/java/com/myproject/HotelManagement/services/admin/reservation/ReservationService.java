package com.myproject.HotelManagement.services.admin.reservation;

import com.myproject.HotelManagement.dto.ReservationResponseDto;

public interface ReservationService {
    ReservationResponseDto getAllReservation(int pageNumber);
    boolean changeStatus(Long id,String status);
}
