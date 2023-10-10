package com.nisum.app.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.app.dao.IUserRepository;
import com.nisum.app.exception.BadRequestException;
import com.nisum.app.model.dto.ResponseDto;
import com.nisum.app.model.dto.UserDto;
import com.nisum.app.model.dto.UserResponse;
import com.nisum.app.service.IUserService;
import com.nisum.app.service.UserServiceImpl;
import com.nisum.app.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IUserService service;

    private final String USERS_API_PATH = "/api/v1/users/";

    @Test
    void shouldCreateNewUser() throws Exception {
        UserDto userDto = TestUtils.USER_DTO;
        UserResponse userResponse = TestUtils.RESPONSE_DTO;;

        when(service.save(any(UserDto.class))).thenReturn(userResponse);

        mockMvc.perform(post(USERS_API_PATH + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.created", equalTo("2023-10-08T13:00:00")))
                .andExpect(jsonPath("$.modified", equalTo("2023-10-08T13:00:00")))
                .andDo(print());
    }

    @Test
    void shouldReturnAndErrorWhenBadArgumentName() throws Exception {
        UserDto userDto = new UserDto("", "abriloscar@abril.org", "Pass-2023", Collections.emptyList());

        when(service.save(any(UserDto.class))).thenThrow(new BadRequestException("El campo name no puede estar vacìo"));

        mockMvc.perform(post(USERS_API_PATH + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El campo name no puede estar vacìo"))
                .andDo(print());
    }
    @Test
    void shouldInactiveUser() throws Exception {
        UUID uuid = UUID.fromString("72b7633a-bf83-4ac0-bfa0-d1b7a6247de1");

        when(service.delete(any(UUID.class))).thenReturn(new ResponseDto("Usuario desactivado con exito"));

        mockMvc.perform(delete(USERS_API_PATH + "/delete/{userUuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Usuario desactivado con exito"))
                .andDo(print());

    }
}