package com.barisceylan.lil.landonhotel.data.repository;

import com.barisceylan.lil.landonhotel.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumberIgnoreCase(String roomNumber);
}
