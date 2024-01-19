package com.example.bookmyshowfeb23.repositories;

import com.example.bookmyshowfeb23.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);

    // either insert or update based on ID
    ShowSeat save(ShowSeat entity);
}
