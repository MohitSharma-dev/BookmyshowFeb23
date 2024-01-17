package dtos;

import models.User;

import java.util.List;

public class BookMovieRequestDTO {
    private List<Long> showSeatIds;
    private Long showId;
    private User userId;
}
