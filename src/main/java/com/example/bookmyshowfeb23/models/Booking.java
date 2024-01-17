package com.example.bookmyshowfeb23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    // 1 : M : One booking will have many showSeats
    // M : 1 : One showSeat can be present in multiple booking -> cancellation
    @ManyToMany
    private List<ShowSeat> showSeats;
    // 1 : 1
    // M : 1
    @ManyToOne
    private User user;
    private Date bookedAt;
    // 1 : 1
    //  M  : 1
    @ManyToOne
    private Show show;
    private int amount;
    // 1 : M
    // 1  : 1
    @OneToMany
    private List<Payment> payments;

}
// Booking table
// column : booking_status
// value : 1 (cancellation) , 4(confirmed)