package com.myproject.HotelManagement.repository;

import com.myproject.HotelManagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {
}
