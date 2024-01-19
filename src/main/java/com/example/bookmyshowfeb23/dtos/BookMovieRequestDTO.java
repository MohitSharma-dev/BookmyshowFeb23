package com.example.bookmyshowfeb23.dtos;

import com.example.bookmyshowfeb23.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDTO {
    private List<Long> showSeatIds;
    private Long showId;
    private Long userId;
}
