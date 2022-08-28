package com.coindirect.recruitment.controller;
import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.RequestBookingDto;
import com.coindirect.recruitment.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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
    @Transactional
    ResponseEntity createBooking(@RequestBody RequestBookingDto requestBooking){

        Boolean isAvailable = bookingRepo.getBookingAvailable(requestBooking.getRow(), requestBooking.getColumn());
        if(isAvailable){
            return ResponseEntity.ok("Error: Booking is not Available");
        }
        int added = bookingRepo.createBooking(requestBooking.getRow(), requestBooking.getColumn(), requestBooking.getName());
        if(added != 0){
            BookingDto booking  = bookingRepo.lastBooking();
            return ResponseEntity.ok(booking);
        }
        return null;
    }

    /**
     * query a booking by grid position
     * @param row grid position row
     * @param column grid position column
     * @return the booking details. 400 if not found
     */
    @GetMapping("getByPosition/{row}/{column}")
    @ResponseBody
    ResponseEntity getBookingByPosition(@PathVariable int row,@PathVariable int column){
        BookingDto booking = bookingRepo.getBookingByPosition(row, column);
        if(booking == null){      
            return ResponseEntity.badRequest().body("");
        }
        return ResponseEntity.ok(booking);
   
    }

    /**
     * query by booking id
     * @param bookingId booking id
     * @return the booking details. 400 if not found
     */
    @GetMapping("getByBookingId/{bookingId}")
    ResponseEntity getBookingById(@PathVariable long bookingId){
        BookingDto booking = bookingRepo.getBookingById(bookingId);
        if(booking == null){      
            return ResponseEntity.badRequest().body("");
        }
        return ResponseEntity.ok(booking);
    }

    /**
     * Query if a cell is available
     * @param row grid position row
     * @param column grid position column
     * @return true if cell is available. false if not
     */
    @GetMapping("isAvailable/{row}/{column}")
    ResponseEntity<Boolean> isAvailable(@PathVariable int row,@PathVariable int column){
        return ResponseEntity.ok(bookingRepo.getBookingAvailable(row,column));
    }

}
