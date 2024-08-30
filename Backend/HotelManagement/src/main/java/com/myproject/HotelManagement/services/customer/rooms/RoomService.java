package com.myproject.HotelManagement.services.customer.rooms;

import com.myproject.HotelManagement.dto.RoomResponse;

public interface RoomService {
    RoomResponse getAvailableRoom(int pageNumbers);
}
