package Model;

public class SeatModel {
    private int movieId;
    private String seatNumber;
    private String bookedByUsername; // can be null if not booked

    public SeatModel(int movieId, String seatNumber, String bookedByUsername) {
        if (movieId <= 0) {
            throw new IllegalArgumentException("Movie ID must be positive.");
        }
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number cannot be null or empty.");
        }
        this.movieId = movieId;
        this.seatNumber = seatNumber;
        this.bookedByUsername = bookedByUsername;
    }

    // Overloaded constructor for unbooked seats
    public SeatModel(int movieId, String seatNumber) {
        this(movieId, seatNumber, null);
    }

    public int getMovieId() {
        return movieId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getBookedByUsername() {
        return bookedByUsername;
    }

    public boolean isBooked() {
        return bookedByUsername != null && !bookedByUsername.isEmpty();
    }

    @Override
    public String toString() {
        return "Seat{" +
                "movieId=" + movieId +
                ", seatNumber='" + seatNumber + '\'' +
                ", bookedByUsername='" + bookedByUsername + '\'' +
                '}';
    }
}
