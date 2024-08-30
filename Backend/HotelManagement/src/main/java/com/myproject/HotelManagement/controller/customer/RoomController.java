package com.myproject.HotelManagement.controller.customer;


import com.myproject.HotelManagement.services.customer.rooms.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class RoomController {
    private final RoomService roomService;
    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAvailableRoom(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomService.getAvailableRoom(pageNumber));
    }
}
