package com.example.AddressBook.Dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordDto {
    @NotBlank(message = "Password cannot be empty")
    private String password;
}

