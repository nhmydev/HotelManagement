package com.myproject.HotelManagement.dto;

import com.myproject.HotelManagement.entity.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomResponse {
    private List<RoomDto> roomDtos;
    private Integer totalPage;
    private Integer pageNumber;
}
