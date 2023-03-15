package com.ebook.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ebook.dto.UserDto;
import com.ebook.entites.User;
import com.ebook.mapper.UserMapper;
import com.ebook.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserById_returnsUserDto() {
        // Arrange
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setId(userId);
        expectedUserDto.setName("John Doe");
        expectedUserDto.setEmail("john.doe@example.com");
        expectedUserDto.setPassword("password");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.userToDto(user)).thenReturn(expectedUserDto);

        // Act
        UserDto actualUserDto = userService.getUserById(userId);

        // Assert
        assertEquals(expectedUserDto, actualUserDto);
    }
}
