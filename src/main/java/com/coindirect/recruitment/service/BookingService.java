package com.coindirect.recruitment.service;

import java.util.List;

import com.coindirect.recruitment.dto.BookingDto;

public interface BookingService {
    // List<Todo> getTodos();

    BookingDto getBookingById(Long id);

    BookingDto createBooking(BookingDto todo);

    // void updateTodo(Long id, Todo todo);

    // void deleteTodo(Long todoId);
}