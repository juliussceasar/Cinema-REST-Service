package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean taken;

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.taken = false;
    }

    public Seat() {}

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}