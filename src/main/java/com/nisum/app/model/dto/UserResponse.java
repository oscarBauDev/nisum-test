package com.nisum.app.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class UserResponse {
    @Schema(name = "UUID", example = "72b7633a-bf83-4ac0-bfa0-d1b7a6247de1")
    private String uuid;
    @Schema(name = "Created", example = "2023-10-08 13:00:00")
    private String created;
    @Schema(name = "Created", example = "2023-10-08 13:00:00")
    private String modified;
    @Schema(name = "Created", example = "2023-10-08 13:00:00")
    private String lastLogin;
    @Schema(name = "Created", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE2OTY4MjQ5NzUsImV4cCI6MTY5NjgyNjQxNX0.4IkRGEVlBU_X2EPCTLY63mQ04vqFWp2ctTaet1Y-tl4")
    private String token;
    @Schema(name = "Created", example = "true")
    private Boolean isActive;

    public UserResponse() {
    }

    public UserResponse(String uuid, String created, String modified, String lastLogin, String token, Boolean isActive) {
        this.uuid = uuid;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
