package com.nisum.app.model.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserDto {

    @NotBlank(message = "El campo name no puede estar vacìo")
    @Schema(name = "User name", example = "Oscar Abril", required = true)
    private String name;
    @NotBlank(message = "El campo correo no puede estar vacìo")
    @Email(message = "El correo debe tener un formato válido")
    @Schema(name = "Email", example = "oabril@abril.org", required = true)
    private String email;
    @NotBlank
    @Schema(name = "Password", example = "Pass-1", required = true)
    private String password;
    @Valid
    @ArraySchema(schema = @Schema(implementation = PhoneDto.class))
    private List<PhoneDto> phones;

    public UserDto() {
    }

    public UserDto(String name, String email, String password, List<PhoneDto> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDto> phones) {
        this.phones = phones;
    }
}
