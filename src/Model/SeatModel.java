package Model;

public class SeatModel {
    private int movieId;
    private String seatNumber;
    private Integer bookedByUserId; // Using Integer to allow null if unbooked
    private String bookedForName; 

    public SeatModel(int movieId, String seatNumber, Integer bookedByUserId, String bookedForName) {
        if (movieId <= 0) {
            throw new IllegalArgumentException("Movie ID must be positive.");
        }
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number cannot be null or empty.");
        }
        this.movieId = movieId;
        this.seatNumber = seatNumber;
        this.bookedByUserId = bookedByUserId;
        this.bookedForName = bookedForName;
    }

    // Overloaded constructor for unbooked seats
    public SeatModel(int movieId, String seatNumber) {
        this(movieId, seatNumber, null, null);
    }

    public int getMovieId() {
        return movieId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Integer getBookedByUserId() {
        return bookedByUserId;
    }

    public String getBookedForName() {
        return bookedForName;
    }

    public boolean isBooked() {
        return bookedByUserId != null;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "movieId=" + movieId +
                ", seatNumber='" + seatNumber + '\'' +
                ", bookedByUserId=" + bookedByUserId +
                ", bookedForName='" + bookedForName + '\'' +
                '}';
    }
}
