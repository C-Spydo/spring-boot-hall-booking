package com.coindirect.recruitment.repository;

import com.coindirect.recruitment.dto.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    //     /* Get Booking by row and column */
    // public BookingDto getBookingByPosition(int row, int column) {
    //     BookingDto items = template.queryForObject("SELECT * FROM bookings WHERE row=? AND column=?", (result,
    //             rowNum) -> new BookingDto(result.getLong("bookingId"), result.getLong("row"), result.getLong("column"), result.getString("name")),
    //             row,column);
    //     return items;
    // }

    // /* Get Booking by bookingId */
    // public BookingDto getBookingByPosition(int bookingId) {
    //     BookingDto items = template.queryForObject("SELECT * FROM bookings WHERE bookingId=?", (result,
    //             rowNum) -> new BookingDto(result.getLong("bookingId"), result.getLong("row"), result.getLong("column"), result.getString("name")),
    //             bookingId);
    //     return items;
    // }

    // /* Check if Booking is Available Booking*/
    // public BookingDto getBookingAvailable(String row, String column) {
    //     BookingDto items = template.queryForObject("SELECT * FROM bookings WHERE row=? AND column=? AND name=NULL", (result,
    //             rowNum) -> new BookingDto(result.getLong("bookingId"), result.getLong("row"), result.getLong("column"), result.getString("name")),
    //             row,column);
    //     return items;
    // }


    // /* Getting all */
    // public List<BookingDto> getAllBookings() {
    //     List<BookingDto> items = template.query("select bookingId,row, column, name from bookings", (result,
    //             rowNum) -> new BookingDto(result.getLong("bookingId"), result.getLong("row"), result.getLong("column"), result.getString("name")));
    //     return items;
    // }

    // /* Getting all name by query string */
    // public List<Notes> getSearch(String search) {
    //     List<Notes> items = template.query("SELECT * FROM NOTES WHERE body LIKE ?", (result,
    //             rowNum) -> new Notes(result.getLong("id"), result.getString("body")),
    //             "%" + search + "%");
    //     return items;
    // }


        /**
     *  get the last note
     */
    public BookingDto lastBooking() {
        BookingDto items = template.queryForObject("SELECT * from bookings WHERE id=(SELECT max(id) FROM bookings)", (result,
                rowNum) -> new BookingDto(result.getLong("id"), result.getInt("brow"), result.getInt("bcolumn"), result.getString("name")));
        return items;
    }


    // /* Getting by id */
    // public Notes getNote(int search) {
    //     Notes items = template.queryForObject("SELECT * FROM NOTES WHERE ID=?", (result,
    //             rowNum) -> new Notes(result.getLong("id"), result.getString("body")),
    //             search);
    //     return items;
    // }

    // /** 
    //  * update a note or insert a note if tat ID doesn't exist
    // */
    // public int updateNote(int id, String body) {
    //     String checkid = "SELECT * FROM NOTES WHERE ID=?1";
    //     String insertion = "INSERT INTO NOTES (body) VALUES(?2)";
    //     String updated = "UPDATE  NOTES SET body = ?2 WHERE ID = ?1";
    //     Notes items = template.queryForObject(checkid, (result, rowNum) -> new Notes(result.getLong("id"), result.getString("body")), id);
    //     //if the body of the note we tried to get does isn't empty, do updated, otherwise, do insertion
    //     if (items.getBody() != "") {
    //         return template.update(updated, id, body);
    //     } else {
    //        return template.update(insertion, body);
    //     }
       
    // }
    // /**
    //  *  get the last note
    //  */
    // public Notes lastNote() {
    //     Notes items = template.queryForObject("SELECT * from NOTES WHERE id=(SELECT max(id) FROM NOTES)", (result,
    //             rowNum) -> new Notes(result.getLong("id"), result.getString("body")));
    //     return items;
    // }

    // /* Delete an item */
    // public int deleteItem(int id) {
    //     String query = "DELETE FROM NOTES WHERE ID =?";
    //     return template.update(query, id);
    // }
}
