package com.ebook.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ebook.dto.CartDto;
import com.ebook.entites.Books;
import com.ebook.entites.Cart;
import com.ebook.entites.User;
import com.ebook.exception.ResourceNotFoundException;
import com.ebook.mapper.CartMapper;
import com.ebook.repository.CartRepository;
import com.ebook.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartMapper cartMapper;

    private Cart cart;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Books book = new Books();
        book.setId(1);
        book.setPrice(20.0);

        User user = new User();
        user.setId(1);

        cart = new Cart();
        cart.setId(1);
        cart.setBook(book);
        cart.setUser(user);
        cart.setQuantity(2);
        cart.setTotalPrice(40.0);
    }

    @Test
    public void testDeleteCart() {
        // Given
        Integer cartId = 1;

        // When
        cartRepository.deleteById(cartId);

        // Then
        assertFalse(cartRepository.existsById(cartId));
    }
}
