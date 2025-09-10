package com.example.SRK.Backend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;


@Entity
@Table(name = "form_submission")
@Data
public class FormSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formType;

    @NotBlank(message = "name is Required")
    @Pattern(regexp = "^[a-zA-Z ]+$\", message = \"Invalid name. Only letters and spaces are allowed.\"")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Phone number is Required")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number. Must be 10 digits.")
    private String phone;

    @ElementCollection
    @CollectionTable(
            name = "form_additional_data",
            joinColumns = @JoinColumn(name = "form_submission_id")
    )
    @MapKeyColumn(name = "field")
    @Column(name = "value")
    private Map<String, String> additionalData;

    private LocalDateTime createdAt = LocalDateTime.now();
}
