package com.coindirect.recruitment.repository;

import com.coindirect.recruitment.dto.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingRepository {

    @Autowired
    JdbcTemplate template;

    /* Adding into database table */
    public int createBooking(int row, int column, String name) {
        String query = "INSERT INTO BOOKINGS (brow, bcolumn, name) VALUES(?,?,?)";
        return template.update(query, row, column, name);
    }

        /* Get Booking by row and column */

    public BookingDto getBookingById(long bookingId) {
        try{
            BookingDto items = template.queryForObject("SELECT * FROM BOOKINGS WHERE id=?", (result,
                    rowNum) -> new BookingDto(result.getLong("bookingId"), result.getInt("row"), result.getInt("column"), result.getString("name")),
                    bookingId);
            return items;
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    /* Get Booking by row and column */
    public BookingDto getBookingByPosition(int row, int column) {
        try{
            BookingDto items = template.queryForObject("SELECT * FROM BOOKINGS WHERE brow=? AND bcolumn=?", (result,
                    rowNum) -> new BookingDto(result.getLong("bookingId"), result.getInt("row"), result.getInt("column"), result.getString("name")),
                    row,column);
            return items;
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    // /* Check if Booking is Available Booking*/
    public Boolean getBookingAvailable(int row, int column) {
        try{
            BookingDto items = template.queryForObject("SELECT * FROM BOOKINGS WHERE brow=? AND bcolumn=?", (result,
                    rowNum) -> new BookingDto(result.getLong("id"), result.getInt("brow"), result.getInt("bcolumn"), result.getString("name")),
                    row,column);
            return items == null ?  false : true;
        }
        catch(EmptyResultDataAccessException e) {
            return false;
        }
        catch(IncorrectResultSizeDataAccessException e) {
            return false;
        }
    }


    /* Getting all */
    public List<BookingDto> getAllBookings() {
        List<BookingDto> items = template.query("select id, brow, bcolumn, name from bookings", (result,
                rowNum) -> new BookingDto(result.getLong("id"), result.getInt("brow"), result.getInt("bcolumn"), result.getString("name")));
        return items;
    }

        /**
     *  get the last booking
     */
    public BookingDto lastBooking() {
        BookingDto items = template.queryForObject("SELECT * from bookings WHERE id=(SELECT max(id) FROM bookings)", (result,
                rowNum) -> new BookingDto(result.getLong("id"), result.getInt("brow"), result.getInt("bcolumn"), result.getString("name")));
        return items;
    }

    /* Delete an item */
    public int deleteBookingById(int id) {
        String query = "DELETE FROM BOOKINGS WHERE ID =?";
        return template.update(query, id);
    }
}
