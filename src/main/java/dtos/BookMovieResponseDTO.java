package dtos;

public class BookMovieResponseDTO {
    private ResponseStatus responseStatus;
    private int amount;
    // The booking is not finalised yet
    // It will be finalised once the payment is done
    private Long bookingId;
}
