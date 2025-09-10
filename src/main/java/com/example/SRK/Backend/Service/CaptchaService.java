package com.example.SRK.Backend.Service;

import com.example.SRK.Backend.Model.ReCaptchaResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaService {

    private static final String SECRET_KEY = "6LfMH8IrAAAAAETRykZKazfpGaGCQRq15oHF9ra0"; // replace with your Google secret key
    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verifyCaptcha(String captchaResponse) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", SECRET_KEY);
        params.add("response", captchaResponse);

        ReCaptchaResponse googleResponse = restTemplate.postForObject(
                VERIFY_URL,
                params,
                ReCaptchaResponse.class
        );

        return googleResponse != null && googleResponse.isSuccess();
    }
}