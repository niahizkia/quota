package com.quota.quota.dto;

import com.quota.quota.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAccountDTO {

    @UniqueUsername(message = "Username ini sudah terpakai.")
    @NotBlank(message = "Username wajib diinput")
    @Size(max = 20, message = "Username max 20 karakter.")
    private String username;

    @NotBlank(message = "Password wajib diinput")
    @Size(max = 20, message = "Password maximum 20 karakter.")
    private String password;

    @NotBlank(message = "Password Confirmation wajib diinput")
    private String passwordConfirmation;

    @NotBlank(message = "Role wajib diinput")
    private String roleName;
}
