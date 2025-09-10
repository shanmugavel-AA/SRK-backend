package com.example.SRK.Backend.Controller;

import com.example.SRK.Backend.Model.FormSubmission;
import com.example.SRK.Backend.Repository.FormRepository;
import com.example.SRK.Backend.Service.CaptchaService;
import com.example.SRK.Backend.Service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forms")
@CrossOrigin(origins = "https://darkcyan-woodcock-495538.hostingersite.com/")
public class FormController {

    private final FormRepository formRepository;
    private final EmailService emailService;
    private CaptchaService captchaService;

    public FormController(FormRepository formRepository, EmailService emailService, CaptchaService captchaService) {
        this.formRepository = formRepository;
        this.emailService = emailService;
        this.captchaService = captchaService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitForm(@Valid @RequestBody FormSubmission submission ) {
        try {
            String captcha = submission.getAdditionalData().get("captcha");
            if (captcha != null) {
                boolean valid = captchaService.verifyCaptcha(captcha);
                if (!valid) return ResponseEntity.badRequest().body("Captcha verification failed");
            }
            // Save to DB
            FormSubmission savedSubmission = formRepository.save(submission);

            // Send email
            emailService.sendFormSubmissionEmail(
                    savedSubmission.getFormType(),
                    savedSubmission.getName(),
                    savedSubmission.getEmail(),
                    savedSubmission.getPhone(),
                    savedSubmission.getAdditionalData()
            );

            return ResponseEntity.ok("Form submitted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body("Failed to process form: " + e.getMessage());
        }
    }
}
