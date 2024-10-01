package com.laterya.hearing.model.dto.user;

import lombok.Data;

@Data
public class AddEmergencyNumberRequest {
    /**
     * 用户id
     */
    private Long userId;
    private String emergencyNumber;
}
