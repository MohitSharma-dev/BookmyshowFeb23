package com.example.bookmyshowfeb23.repositories;

import com.example.bookmyshowfeb23.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Override
    Booking save(Booking entity);
}
