package com.nisum.app.util;

import com.nisum.app.model.Phone;
import com.nisum.app.model.User;
import com.nisum.app.model.dto.PhoneDto;
import com.nisum.app.model.dto.UserDto;
import com.nisum.app.model.dto.UserResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestUtils {
    public static PhoneDto PHONE1 = new PhoneDto("9876543", "1", "57");
    public static Phone PHONE = new Phone(1L, "9876543", "1", "57");
    public static List<PhoneDto> PHONES_DTO = Arrays.asList(PHONE1);

    public static List<PhoneDto> PHONES = Arrays.asList(PHONE1);
    public static UserDto USER_DTO = new UserDto("Charles Baudelaire", "cbaudelaire@baudelaire.org", "Pass-2023", PHONES_DTO);

    public static User USER = new User("Charles Baudelaire", "cbaudelaire@baudelaire.org", "Pass-2023", Collections.emptyList(), true);
    public static UserResponse RESPONSE_DTO = new UserResponse("72b7633a-bf83-4ac0-bfa0-d1b7a6247de1", "2023-10-08T13:00:00", "2023-10-08T13:00:00", "2023-10-08T13:00:00", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE2OTY4NzY0NDYsImV4cCI6MTY5Njg3Nzg4Nn0.Pm8s6QfaTPal-Drm_XnL5KSqzZIGwI70kyDY2RYol9Q", true);

    public static UserResponse RESPONSE_DTO_UPDATE = new UserResponse("72b7633a-bf83-4ac0-bfa0-d1b7a6247de1", "2023-10-08T13:00:00", "2023-10-08T13:10:00", "2023-10-08T13:10:00", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE2OTY4ODM4NDcsImV4cCI6MTY5Njg4NTI4N30.ss16qFuFqZTP2zNlUBXDX1wvG-nKTBDFuWpYc1lRDAY", true);
}
