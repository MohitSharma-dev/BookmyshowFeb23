package com.example.bookmyshowfeb23.repositories;

import com.example.bookmyshowfeb23.models.Show;
import com.example.bookmyshowfeb23.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jndi.JndiPropertySource;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByShow(Show show);
}
