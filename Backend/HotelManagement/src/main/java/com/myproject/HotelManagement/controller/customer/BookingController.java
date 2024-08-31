package com.myproject.HotelManagement.controller.customer;

import com.myproject.HotelManagement.dto.ReservationDto;
import com.myproject.HotelManagement.services.customer.reservation.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> bookingRoom(@RequestBody ReservationDto reservationDto) {
        boolean success = bookingService.postReservation(reservationDto);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/booking/{userid}/{pageNumber}")
    public ResponseEntity<?> getAllBooking(@PathVariable Long userid, @PathVariable int pageNumber){
        try {
            return ResponseEntity.ok(bookingService.getAllReser(userid,pageNumber));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
