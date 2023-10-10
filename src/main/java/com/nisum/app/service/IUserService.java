package com.nisum.app.service;

import com.nisum.app.model.User;
import com.nisum.app.model.dto.ResponseDto;
import com.nisum.app.model.dto.UserDto;
import com.nisum.app.model.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    List<User> getAll();
    UserResponse save(UserDto user);
    UserResponse update(Long id, UserDto user);
    ResponseDto delete(UUID uuid);
}
