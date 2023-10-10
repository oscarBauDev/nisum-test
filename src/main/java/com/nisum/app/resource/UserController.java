package com.nisum.app.resource;

import com.nisum.app.model.User;
import com.nisum.app.model.dto.ResponseDto;
import com.nisum.app.model.dto.UserDto;
import com.nisum.app.model.dto.UserResponse;
import com.nisum.app.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll(){
        List<User> users = service.getAll();
        return users;
        //return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDto));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
                                                   @Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(service.update(userId, userDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userUuid}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable UUID userUuid){
        System.out.println("En el delete");
        return new ResponseEntity<>(service.delete(userUuid), HttpStatus.OK);
    }
}
