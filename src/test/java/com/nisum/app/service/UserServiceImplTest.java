package com.nisum.app.service;

import com.nisum.app.dao.IUserRepository;
import com.nisum.app.exception.BadRequestException;
import com.nisum.app.model.Phone;
import com.nisum.app.model.User;
import com.nisum.app.model.dto.PhoneDto;
import com.nisum.app.model.dto.UserDto;
import com.nisum.app.model.dto.UserResponse;
import com.nisum.app.util.JwtToken;
import com.nisum.app.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserServiceImpl.class)
class UserServiceImplTest {

    @MockBean
    private IUserRepository repository;
    @MockBean
    private ModelMapper mapper;
    @MockBean
    private JwtToken jwtToken;
    @Autowired
    private UserServiceImpl service;


    @Test
    void createUserWithSuccess(){
        UserDto userDto = TestUtils.USER_DTO;
        User user = TestUtils.USER;

        when(mapper.map(userDto, User.class)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(jwtToken.generateToken(userDto.getEmail())).thenReturn("mockedToken");

        service.save(userDto);

        verify(mapper, times(1)).map(userDto, User.class);
        verify(repository, times(1)).save(user);
        verify(jwtToken, times(1)).generateToken(userDto.getEmail());
    }

    @Test
    void createUserWithInvalidPasswprdFormatError(){
        UserDto userDto = new UserDto("Oscar Abril", "abriloscar@abril.org", "pass-2023", Collections.emptyList());

        Assertions.assertThrows(BadRequestException.class, () -> service.save(userDto));

    }

    @Test
    void createUserWithInvalidEmailFormatError(){
        UserDto userDto = new UserDto("Oscar Abril", "oscar@correo.org", "Pass-2023", Collections.emptyList());

        when(repository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(new User()));

        Assertions.assertThrows(BadRequestException.class, () -> service.save(userDto));
    }

    @Test
    void inactivateUserWithSuccess(){
        User user = TestUtils.USER;
        user.setActive(true);
        UUID uuid = UUID.fromString("72b7633a-bf83-4ac0-bfa0-d1b7a6247de1");

        when(repository.findByUuid(uuid)).thenReturn(Optional.ofNullable(user));

        service.delete(uuid);

        verify(repository, times(1)).findByUuid(uuid);
    }

    @Test
    void inactivateUserWithInactivateUserError(){
        User user = TestUtils.USER;
        user.setActive(false);
        UUID uuid = UUID.fromString("72b7633a-bf83-4ac0-bfa0-d1b7a6247de1");

        when(repository.findByUuid(uuid)).thenReturn(Optional.ofNullable(user));

        Assertions.assertThrows(BadRequestException.class, () -> service.delete(uuid));;
    }

    @Test
    void inactivateUserWithUuidError(){
        User user = TestUtils.USER;
        UUID uuid = UUID.fromString("72b7633a-bf83-4ac0-bfa0-d1b7a6247de1");

        when(repository.findByUuid(uuid)).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, () -> service.delete(uuid));;
    }
}