import java.util.Scanner;

class Movie {
    private String movieName;
    private String showTime;

    public Movie(String movieName, String showTime) {
        this.movieName = movieName;
        this.showTime = showTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void displayMovie() {
        System.out.println("Movie: " + movieName);
        System.out.println("Show Time: " + showTime);
    }
}

class Seat {
    String type;
    double price;
    boolean isBooked;

    public Seat(String type, double price) {
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

class Booking {
    private Seat[][] seats;
    private double totalCost = 0;

    public Booking(int rows, int cols) {
        seats = new Seat[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (i < 2) {
                    seats[i][j] = new Seat("Premium", 250);
                } else {
                    seats[i][j] = new Seat("Regular", 150);
                }
            }
        }
    }

    public void displaySeats() {
        System.out.println("\nSeat Layout:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isBooked) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    public boolean bookSeat(int row, int col) {

        if (row < 0 || row >= seats.length ||
            col < 0 || col >= seats[0].length) {

            System.out.println("Invalid Seat!");
            return false;
        }

        if (seats[row][col].isBooked) {
            System.out.println("Seat already booked!");
            return false;
        }

        seats[row][col].isBooked = true;
        totalCost += seats[row][col].price;

        System.out.println("Seat Booked Successfully!");
        System.out.println("Seat Type: " + seats[row][col].type);
        System.out.println("Price: ₹" + seats[row][col].price);

        return true;
    }

    public double getTotalCost() {
        return totalCost;
    }
}

public class MovieTicketBookingSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Movie movie = new Movie("Avengers: Endgame", "7:00 PM");

        Booking booking = new Booking(4, 4);

        movie.displayMovie();

        booking.displaySeats();

        System.out.print("\nHow many seats do you want to book? ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nBooking Seat " + i);

            System.out.print("Enter Row (1-4): ");
            int row = sc.nextInt();

            System.out.print("Enter Column (1-4): ");
            int col = sc.nextInt();

            booking.bookSeat(row - 1, col - 1);
        }

        booking.displaySeats();

        System.out.println("\n----------------------------");
        System.out.println("BOOKING CONFIRMATION");
        System.out.println("----------------------------");
        System.out.println("Movie: " + movie.getMovieName());
        System.out.println("Show Time: " + movie.getShowTime());
        System.out.println("Total Cost: ₹" + booking.getTotalCost());
        System.out.println("Booking Confirmed!");
        System.out.println("----------------------------");

        sc.close();
    }
}