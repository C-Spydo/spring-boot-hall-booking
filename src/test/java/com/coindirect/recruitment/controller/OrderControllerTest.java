package com.coindirect.recruitment.controller;

import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.RequestBookingDto;
import com.coindirect.recruitment.repository.BookingRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertEquals;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest
public class OrderControllerTest {

@Autowired
    private MockMvc mockMvc;

    @MockBean
    private  BookingRepository bookingRepo;

    BookingDto booking = new BookingDto(2, 3, 4,"David Djikstra");

    @Test
	public void createBooking() throws Exception {
		RequestBookingDto booking = new RequestBookingDto(3, 4,"David Djikstra");
        String exampleBookingJson = "{\"id\":2,\"row\":3,\"column\":4,\"name\":\"David Djikstra\"}";

		Mockito.when(bookingRepo.createBooking(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString()
						)).thenReturn(1);


		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/create")
				.accept(MediaType.APPLICATION_JSON).content(exampleBookingJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void getBookingByPosition() throws Exception {

		Mockito.when(bookingRepo.getBookingByPosition(Mockito.anyInt(),
						Mockito.anyInt())).thenReturn(booking);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getByPosition/3/2").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\":2,\"row\":3,\"column\":4,\"name\":\"David Djikstra\"}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

    @Test
	public void getBookingById() throws Exception {

		Mockito.when(bookingRepo.getBookingById(Mockito.anyLong()
						)).thenReturn(booking);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getByBookingId/2").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\":2,\"row\":3,\"column\":4,\"name\":\"David Djikstra\"}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

    @Test
	public void isAvailable() throws Exception {

		Mockito.when(bookingRepo.getBookingByPosition(Mockito.anyInt(),
						Mockito.anyInt())).thenReturn(booking);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/isAvailable/2/5").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		Boolean expected = true;
        assertEquals(expected, result.getResponse());
	}
}
