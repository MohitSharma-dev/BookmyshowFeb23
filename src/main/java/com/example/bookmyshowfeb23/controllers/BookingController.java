package com.example.bookmyshowfeb23.controllers;

import com.example.bookmyshowfeb23.dtos.BookMovieRequestDTO;
import com.example.bookmyshowfeb23.dtos.BookMovieResponseDTO;
import com.example.bookmyshowfeb23.dtos.ResponseStatus;
import com.example.bookmyshowfeb23.models.Booking;
import com.example.bookmyshowfeb23.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public BookMovieResponseDTO bookTicket(BookMovieRequestDTO bookMovieRequestDTO){
        BookMovieResponseDTO response = new BookMovieResponseDTO();
        try {
            Booking booking = bookingService.bookTicket(
                    bookMovieRequestDTO.getShowSeatIds(),
                    bookMovieRequestDTO.getShowId(),
                    bookMovieRequestDTO.getUserId()
            );
//            response.setResponseStatus(ResponseStatus.SUCCESS).setBookingId()
            response.setBookingId(booking.getId())
                    .setAmount(booking.getAmount())
                    .setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
