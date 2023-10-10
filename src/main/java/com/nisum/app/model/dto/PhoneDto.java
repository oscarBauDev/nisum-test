package com.nisum.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class PhoneDto {

    private Long id;

    @NotBlank(message = "El campo number no puede esatr vacìo")
    @Schema(name = "Numer", example = "3221166", required = true)
    private String number;
    @NotBlank(message = "El campo citycode no puede esatr vacìo")
    @JsonProperty("citycode")
    @Schema(name = "City code", example = "1", required = true)
    private String cityCode;
    @NotBlank(message = "El campo countrycode no puede esatr vacìo")
    @JsonProperty("countrycode")
    @Schema(name = "Country code", example = "57", required = true)
    private String countryCode;

    public PhoneDto() {
    }

    public PhoneDto(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public PhoneDto(Long id, String number, String cityCode, String countryCode) {
        this.id = id;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
