package com.example.bookmyshowfeb23.dtos;

import com.example.bookmyshowfeb23.models.User;

import java.util.List;

public class BookMovieRequestDTO {
    private List<Long> showSeatIds;
    private Long showId;
    private User userId;
}
