package com.example.bookmyshowfeb23.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BookMovieResponseDTO {
    private ResponseStatus responseStatus;
    private int amount;
    // The booking is not finalised yet
    // It will be finalised once the payment is done
    private Long bookingId;
}
