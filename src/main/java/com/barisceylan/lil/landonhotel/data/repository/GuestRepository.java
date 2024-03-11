package com.barisceylan.lil.landonhotel.data.repository;

import com.barisceylan.lil.landonhotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

}
