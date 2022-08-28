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


}
