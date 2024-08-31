package com.myproject.HotelManagement.repository;

import com.myproject.HotelManagement.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {
    Page<Reservation> findAllByUserId(Pageable pageable, Long id);
}
