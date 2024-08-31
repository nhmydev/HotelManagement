package com.myproject.HotelManagement.controller.admin;

import com.myproject.HotelManagement.services.admin.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservation/{pageNumber}")
    public ResponseEntity<?> getAllRever(@PathVariable int pageNumber){
        try {
            return ResponseEntity.ok(reservationService.getAllReservation(pageNumber));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @GetMapping("reservation/{id}/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id,@PathVariable String status){
        boolean success = reservationService.changeStatus(id,status);
        if (success){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
 }
