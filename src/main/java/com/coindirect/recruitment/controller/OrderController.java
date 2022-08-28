package com.coindirect.recruitment.controller;

import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.DeleteDto;
import com.coindirect.recruitment.dto.RequestBookingDto;
import com.coindirect.recruitment.repository.BookingRepository;
import com.coindirect.recruitment.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import com.coindirect.repository.BookingRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.List;

/**
 * Controller for handling bookings for a bing hall.
 * The hall can be imagined as a grid of rows and columns
 */
@RestController("orders")
public class OrderController {

    // BookingService bookingService;
    @Autowired
    BookingRepository bookingRepo;

    /**
     * Creates a booking with the requested details.
     * If unavailable returns a 200 with error message.
     * @param requestBooking the requested booking details.
     * @return on success booking details. on failure error message.
     */
    
    @PostMapping("create")
    @ResponseBody
    ResponseEntity<BookingDto> createBooking(@RequestBody RequestBookingDto requestBooking){
        BookingDto booking;
        int added = bookingRepo.createBooking(requestBooking.getRow(), requestBooking.getColumn(), requestBooking.getName());
        if(added != 0){
            booking = bookingRepo.lastBooking();
            HttpHeaders httpHeaders = new HttpHeaders();
            return new ResponseEntity<>(booking, httpHeaders, HttpStatus.CREATED);
        }

        return null;

        // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        // int added = 0;

        // payload = null;
        // JSONParser parser = new JSONParser();
        // Object object = parser.parse(payload);
        // JSONObject jsonObject = (JSONObject) object;

        // int row = jsonObject.get("row");
        // int column = jsonObject.get("column");
        // if(bookingRepo.getBookingAvailable(row,column) !== NULL){
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        // }

        // String body = (String) jsonObject.get("body");

        // added = bookingRepo.createBooking(body);
            
        // requestBooking = requestBooking.lastNote();
        
        // return new RequestBookingDto(requestBooking.getId(), requestBooking.getBody());

        // return null;
    }

    /**
     * query a booking by grid position
     * @param row grid position row
     * @param column grid position column
     * @return the booking details. 400 if not found
     */
    @GetMapping("getByPosition/{row}/{column}")
    ResponseEntity<BookingDto> getBookingByPosition(@PathVariable String row,@PathVariable String column){
        // added = bookingRepo.createBooking(body);
       return null;
    }

    /**
     * query by booking id
     * @param bookingId booking id
     * @return the booking details. 400 if not found
     */
    @GetMapping("getByBookingId/{bookingId}")
    ResponseEntity<BookingDto> getBookingById(@PathVariable String bookingId){
        return null;
    }

    /**
     * Query if a cell is available
     * @param row grid position row
     * @param column grid position column
     * @return true if cell is available. false if not
     */
    @GetMapping("isAvailable/{row}/{column}")
    ResponseEntity<Boolean> isAvailable(@PathVariable String row,@PathVariable String column){
        return null;
        // bookingRepo.getBookingAvailable(row,column) == null ? return true : return false;
    }

}
