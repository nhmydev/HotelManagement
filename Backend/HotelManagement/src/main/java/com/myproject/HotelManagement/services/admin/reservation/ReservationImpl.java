package com.myproject.HotelManagement.services.admin.reservation;

import com.myproject.HotelManagement.dto.ReservationResponseDto;
import com.myproject.HotelManagement.entity.Reservation;
import com.myproject.HotelManagement.entity.Room;
import com.myproject.HotelManagement.enums.ReserStatus;
import com.myproject.HotelManagement.repository.ReservationRepo;
import com.myproject.HotelManagement.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationImpl implements ReservationService{
    private final ReservationRepo reservationRepo;
    private final RoomRepo roomRepo;
    public static final int Search_page = 4;

    public ReservationResponseDto getAllReservation(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,Search_page);
        Page<Reservation> reservationPage = reservationRepo.findAll(pageable);
        ReservationResponseDto reservationResponseDto =  new ReservationResponseDto();

        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getreservationDto).collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
        return reservationResponseDto;
    }


    public boolean changeStatus(Long id,String status){
        Optional<Reservation> reservation = reservationRepo.findById(id);
        if (reservation.isPresent()){
            Reservation reserFound = reservation.get();
            if (Objects.equals(status,"Approve")){
                reserFound.setReservationStatus(ReserStatus.APPROVED);
            }else {
                reserFound.setReservationStatus(ReserStatus.REJECTED);
            }
            reservationRepo.save(reserFound);
            Room existRoom = reserFound.getRoom();
            existRoom.setBooked(true);
            roomRepo.save(existRoom);
            return true;
        }
        return false;
    }
}
