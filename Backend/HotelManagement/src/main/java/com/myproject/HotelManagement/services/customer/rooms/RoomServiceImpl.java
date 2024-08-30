package com.myproject.HotelManagement.services.customer.rooms;


import com.myproject.HotelManagement.dto.RoomResponse;
import com.myproject.HotelManagement.entity.Room;
import com.myproject.HotelManagement.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepo roomRepo;

    public RoomResponse getAvailableRoom(int pageNumbers){
        Pageable pageable = PageRequest.of(pageNumbers,6);
        Page<Room> roomPage =  roomRepo.findBybooked(false,pageable);

        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setTotalPage(roomPage.getTotalPages());
        roomResponse.setPageNumber(roomPage.getPageable().getPageNumber());
        roomResponse.setRoomDtos(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
        return roomResponse;
    }
}
