package com.myproject.HotelManagement.services.customer.reservation;

import com.myproject.HotelManagement.dto.ReservationDto;
import com.myproject.HotelManagement.dto.ReservationResponseDto;

public interface BookingService {
    boolean postReservation(ReservationDto reservationDto);
    ReservationResponseDto getAllReser(Long userid, int pageNumber);
}
