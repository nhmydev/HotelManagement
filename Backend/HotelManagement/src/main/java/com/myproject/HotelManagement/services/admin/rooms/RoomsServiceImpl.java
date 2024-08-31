package com.myproject.HotelManagement.services.admin.rooms;

import com.myproject.HotelManagement.dto.RoomDto;
import com.myproject.HotelManagement.dto.RoomResponse;
import com.myproject.HotelManagement.entity.Room;
import com.myproject.HotelManagement.repository.RoomRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService{
    private final RoomRepo roomRepo;

    public boolean postRoom(RoomDto roomDto){
        try {
            Room room = new Room();
            room.setName(roomDto.getName());
            room.setType(roomDto.getType());
            room.setPrice(roomDto.getPrice());
            room.setBooked(false);
            roomRepo.save(room);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public RoomResponse getAll(int pageNumbers){
        Pageable pageable = PageRequest.of(pageNumbers,4);
        Page<Room> roomPage =  roomRepo.findAll(pageable);

        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setTotalPage(roomPage.getTotalPages());
        roomResponse.setPageNumber(roomPage.getPageable().getPageNumber());
        roomResponse.setRoomDtos(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
        return roomResponse;
    }

    public RoomDto getRoomById(Long id){
        Optional<Room> room = roomRepo.findById(id);
        if(room.isPresent()){
            return room.get().getRoomDto();
        }
        else {
            throw new EntityNotFoundException("Room not found");
        }

    }
    public boolean updateRoom(Long id,RoomDto roomDto){
        Optional<Room> room = roomRepo.findById(id);
        if(room.isPresent()){
            Room roomFound = room.get();
            roomFound.setName(roomDto.getName());
            roomFound.setType(roomDto.getType());
            roomFound.setPrice(roomDto.getPrice());
            roomRepo.save(roomFound);
            return true;
        }
        return false;
    }
    public void deleteroom(Long id){
        Optional<Room>room = roomRepo.findById(id);
        if(room.isPresent()){
            roomRepo.deleteById(id);
        }else {
            throw new EntityNotFoundException("Room not found");
        }
    }
}
