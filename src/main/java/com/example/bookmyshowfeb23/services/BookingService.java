package com.example.bookmyshowfeb23.services;

import com.example.bookmyshowfeb23.models.*;
import com.example.bookmyshowfeb23.repositories.BookingRepository;
import com.example.bookmyshowfeb23.repositories.ShowRepository;
import com.example.bookmyshowfeb23.repositories.ShowSeatRepository;
import com.example.bookmyshowfeb23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private PriceCalculatorService priceCalculatorService;
    @Autowired
    public BookingService(
            UserRepository userRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository,
            BookingRepository bookingRepository,
            PriceCalculatorService priceCalculatorService
    ){
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }
    @Transactional(isolation =  Isolation.SERIALIZABLE)
    public Booking bookTicket(
            List<Long> showSeatIds,
            Long showId,
            Long userId
    ){
        // 1. Get a user with UserId
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found!");
        }
        User bookedBy = userOptional.get();
        // 2. Get the corresponding show with ShowId
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException("Show doesn't exist!");
        }
        Show savedShow = showOptional.get();
        // ---------- TAKE LOCK --------------- => WILL BE TAKEN UP IN PROJECT MODULE
        // 3. Get ShowSeat with showSeatIds from db
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        // 4. check if the all the show seats are available
        for(ShowSeat showSeat : showSeats){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.EMPTY) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(),new Date().toInstant()).toMinutes() > 15))){
                // 5. If no, throw an error
                throw new RuntimeException("Not all selected seats are available");
            }
        }

        // 6. If Yes, mark all the show seats as blocked
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            // 7. Save updated show seats in the database
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        // ----------- RELEASE LOCK ----------
        // 8. Create the Booking object and add the details
        Booking booking = new Booking();
        booking.setBookedAt(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShow(savedShow);
        booking.setUser(bookedBy);
        booking.setShowSeats(savedShowSeats);
        booking.setAmount(priceCalculatorService.calculatePrice(savedShowSeats , savedShow));
        booking.setPayments(new ArrayList<>());
        // 9. save it in the db
        // 10. return the Booking object
        return bookingRepository.save(booking);

    }
}

// db
// start transaction;
// select * from film where id = 3;
