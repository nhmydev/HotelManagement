package com.myproject.HotelManagement.services.customer.reservation;

import com.myproject.HotelManagement.dto.ReservationDto;
import com.myproject.HotelManagement.dto.ReservationResponseDto;
import com.myproject.HotelManagement.entity.Reservation;
import com.myproject.HotelManagement.entity.Room;
import com.myproject.HotelManagement.entity.User;
import com.myproject.HotelManagement.enums.ReserStatus;
import com.myproject.HotelManagement.repository.ReservationRepo;
import com.myproject.HotelManagement.repository.RoomRepo;
import com.myproject.HotelManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final UserRepository userRepository;
    private final RoomRepo roomRepo;
    private final ReservationRepo reservationRepo;
    public static final int Search_page = 4;
    public boolean postReservation(ReservationDto reservationDto){
        Optional<User> user = userRepository.findById(reservationDto.getUserId());
        Optional<Room> room = roomRepo.findById(reservationDto.getRoomId());

        if(user.isPresent() && room.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setRoom(room.get());
            reservation.setUser(user.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReserStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(),reservationDto.getCheckOutDate());
            Long price = room.get().getPrice() * days;
            reservation.setPrice(price);
            reservationRepo.save(reservation);
            return true;
        }
        return false;
    }
    public ReservationResponseDto getAllReser(Long userid,int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,Search_page);
        Page<Reservation> reservationPage = reservationRepo.findAllByUserId(pageable,userid);
        ReservationResponseDto reservationResponseDto =  new ReservationResponseDto();

        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getreservationDto).collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
        return reservationResponseDto;

    }}
