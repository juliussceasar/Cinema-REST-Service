package cinema;
import java.util.ArrayList;
import java.util.List;


public class Cinema {
    private int total_rows;
    private int total_columns;
    List<Seat> available_seats;

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new ArrayList<>();
        int price = 10;
        for (int row = 1; row <= total_rows; row++) {
            if (row > 4) {
                price = 8;
            }
            for (int j = 1; j <= total_columns; j++) {
                Seat seat = new Seat(row, j, price);
                available_seats.add(seat);
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }
}