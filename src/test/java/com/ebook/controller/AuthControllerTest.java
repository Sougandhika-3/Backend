package com.ebook.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ebook.dto.UserDto;
import com.ebook.service.UserService;

@SpringBootTest
public class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        ResponseEntity<?> responseEntity = new ResponseEntity<>(userDto, HttpStatus.CREATED);

        when(userService.checkEmail(userDto.getEmail())).thenReturn(false);
        when(userService.createUser(userDto)).thenReturn(userDto);

        ResponseEntity<?> result = authController.createUser(userDto);

        assertEquals(responseEntity, result);
    }
}

