package com.coindirect.recruitment;

import com.coindirect.recruitment.dto.BookingDto;
import com.coindirect.recruitment.dto.RequestBookingDto;

import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = BookingSystemApplication.class, 
    webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
   
	@Sql({ "schema.sql", "data.sql" })
	@Test
	public void testCreateBooking() 
	{
		RequestBookingDto booking = new RequestBookingDto(3, 4,"David Djikstra");
		ResponseEntity responseEntity = this.restTemplate
			.postForEntity("http://localhost:" + port + "/create", booking, String.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testGetBookingByPosition() 
	{
	  assertTrue(
		  this.restTemplate
			.getForObject("http://localhost:" + port + "/getByPosition/3/2", BookingDto.class)
			.getRow() == 3);
	}

	@Test
	public void testGetBookingById() 
	{
	  assertTrue(
		  this.restTemplate
			.getForObject("http://localhost:" + port + "/getByBookingId/1", BookingDto.class)
			.getRow() == 3);
	}

	@Test
	public void testIsAvailable() 
	{
	  assertTrue(
		  this.restTemplate
			.getForObject("http://localhost:" + port + "/isAvailable/5/2", BookingDto.class)
			.getRow() == 3);
	}

}
