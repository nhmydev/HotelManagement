package com.myproject.HotelManagement.services.admin.rooms;

import com.myproject.HotelManagement.dto.RoomDto;
import com.myproject.HotelManagement.dto.RoomResponse;

public interface RoomService {
    boolean postRoom(RoomDto roomDto);
    RoomResponse getAll(int pageNumbers);
    RoomDto getRoomById(Long id);
    boolean updateRoom(Long id,RoomDto roomDto);
    void deleteroom(Long id);
}
