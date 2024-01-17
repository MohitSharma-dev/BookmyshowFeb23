package com.example.bookmyshowfeb23.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated
    private ShowSeatStatus showSeatStatus;

}

// ShowSeat * Show
// 1 : 1
// M  : 1
//Show Seat status
// 1 5 ---
// 1 6 --
// 1 7 --
// 2 5
// 3 5

// 1 ShowSeat object => 1 show * 1 seat
// 1 show => how many showSeat object ? Multiple