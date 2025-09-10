package com.example.SRK.Backend.Model;

import lombok.Data;

import java.util.List;

@Data
public class ReCaptchaResponse {
    private boolean success;
    private List<String> errorCodes;
}
