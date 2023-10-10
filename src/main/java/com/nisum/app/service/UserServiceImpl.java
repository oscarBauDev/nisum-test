package com.nisum.app.service;

import com.nisum.app.dao.IUserRepository;
import com.nisum.app.exception.BadRequestException;
import com.nisum.app.exception.NotFoundException;
import com.nisum.app.model.Phone;
import com.nisum.app.model.User;
import com.nisum.app.model.dto.PhoneDto;
import com.nisum.app.model.dto.ResponseDto;
import com.nisum.app.model.dto.UserDto;
import com.nisum.app.model.dto.UserResponse;
import com.nisum.app.util.JwtToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements IUserService{

    private final IUserRepository repository;
    private final ModelMapper mapper;
    private final JwtToken jwtToken;

    @Value("${password.validation.regex}")
    private String passwordRegex;

    public UserServiceImpl(IUserRepository repository, ModelMapper mapper, JwtToken jwtToken) {
        this.repository = repository;
        this.mapper = mapper;
        this.jwtToken = jwtToken;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public UserResponse save(UserDto userDto) {
        isRegisteredEmail(userDto.getEmail());
        isValidPassword(userDto.getPassword());

        User user = mapper.map(userDto, User.class);
        String token = jwtToken.generateToken(user.getEmail());
        user.setToken(token);

        if(user.getPhones().size() > 0){
            for(Phone phone : user.getPhones()){
                phone.setUser(user);
            }
        }
        user.setUuid(UUID.randomUUID());
        user.setActive(true);
        User savedUser = repository.save(user);

        return mapper.map(savedUser, UserResponse.class);
    }

    @Override
    public UserResponse update(Long id, UserDto userDto) {
        Optional<User> userDb = repository.findById(id);
        if (!userDb.isPresent()) {
            throw new NotFoundException("Usuario con ID " + id + " no encontrado");
        }
        User existingUser = userDb.get();

        if(!existingUser.getEmail().equalsIgnoreCase(userDto.getEmail())){
            isRegisteredEmail(userDto.getEmail());
        }
        if(!Objects.equals(userDto.getPassword(), existingUser.getPassword())){
            isValidPassword(userDto.getPassword());
        }

        existingUser.setEmail(userDto.getEmail());
        existingUser.setName(userDto.getName());
        existingUser.setPassword(userDto.getPassword());

        if(userDto.getPhones().size() > 0){
            for(PhoneDto phoneDto : userDto.getPhones()){
                if(phoneDto.getId() == null){
                    Phone phone = mapper.map(phoneDto, Phone.class);
                    phone.setUser(existingUser);
                    existingUser.addPhone(phone);
                }
                else{
                    for (Phone existingPhone : existingUser.getPhones()) {
                        if (existingPhone.getId() != null && existingPhone.getId().equals(phoneDto.getId())) {
                            existingPhone.setNumber(phoneDto.getNumber());
                            existingPhone.setCityCode(phoneDto.getCityCode());
                            existingPhone.setCountryCode(phoneDto.getCountryCode());
                        }
                    }
                }
            }
        }
        String token = jwtToken.generateToken(existingUser.getEmail());
        existingUser.setToken(token);
        User updatedUser = repository.save(existingUser);
        return mapper.map(updatedUser, UserResponse.class);
    }

    @Override
    public ResponseDto delete(UUID uuid) {
        Optional<User> optionalUser = repository.findByUuid(uuid);
        if(!optionalUser.isPresent()){
            throw new BadRequestException("Usuario con uuid " + uuid + " no existe");
        }
        User user = optionalUser.get();
        if(user.getActive().equals(false)){
            throw new BadRequestException("Usuario con uuid " + uuid + " ya no se encuentra activo");
        }
        user.setActive(false);
        repository.save(user);
        return new ResponseDto("Usuario desactivado con exito");
    }

    private void isRegisteredEmail(String email) {
        if (repository.findByEmail(email).isPresent()) {
            throw new BadRequestException("El correo ya está registado");
        }
    }

    private void isValidPassword(String password) {
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new BadRequestException("Formato de contraseña incorrecto! La contraseña debe contener al menos 1 letra mayúscula, 1 letra minúscula, 1 número y 1 carácter especial, y tener una longitud de 6 a 20 caracteres.");
        }
    }
}
