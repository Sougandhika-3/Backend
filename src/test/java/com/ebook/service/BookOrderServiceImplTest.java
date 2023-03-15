package com.ebook.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ebook.dto.BookOrderDto;
import com.ebook.entites.BookOrder;
import com.ebook.mapper.BookOrderMapper;
import com.ebook.repository.BookOrderRepository;

public class BookOrderServiceImplTest {

	@InjectMocks
	private BookOrderServiceImpl bookOrderService;

	@Mock
	private BookOrderRepository bookOrderRepo;

	@Mock
	private BookOrderMapper bookOrderMapper;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testUpdateOrder() {
		// create mock data
		BookOrder bkOrd = new BookOrder();
		bkOrd.setId(1);
		bkOrd.setStatus("Order Processing");
		BookOrder updtbkOrd = new BookOrder();
		updtbkOrd.setId(1);
		updtbkOrd.setStatus("Order Shipped");
		BookOrderDto bkOrdDto = new BookOrderDto();
		bkOrdDto.setId(1);
		bkOrdDto.setStatus("Order Shipped");

		// set up mock repository
		when(bookOrderRepo.findById(1)).thenReturn(Optional.of(bkOrd));
		when(bookOrderRepo.save(bkOrd)).thenReturn(updtbkOrd);
		when(bookOrderMapper.bookOrderToDto(updtbkOrd)).thenReturn(bkOrdDto);

		// call service method
		BookOrderDto updatedBkOrdDto = bookOrderService.updateOrder(1, "Order Shipped");

		// assert that the updated book order DTO matches the expected values
		assertEquals(bkOrdDto.getId(), updatedBkOrdDto.getId());
		assertEquals(bkOrdDto.getStatus(), updatedBkOrdDto.getStatus());
	}
}
