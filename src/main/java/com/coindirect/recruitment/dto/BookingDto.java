package com.coindirect.recruitment.dto;

public class BookingDto {

    private long bookingId;
    private int row;
    private int column;
    private String name;

    public BookingDto(long bookingId, int row, int column, String name) {
        this.bookingId = bookingId;
        this.row = row;
        this.column = column;
        this.name = name;
    }


    public long getId() {
        return bookingId;
    }

    public void setId(long bookingId) {
        this.bookingId = bookingId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
