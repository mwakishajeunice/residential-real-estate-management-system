package com.jeunice.realestate.dao;

import com.jeunice.realestate.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query( value = "SELECT * FROM bookings u WHERE u.house_id = ?", nativeQuery = true)
    List<Booking> findAllByHouseId(long id);
}
