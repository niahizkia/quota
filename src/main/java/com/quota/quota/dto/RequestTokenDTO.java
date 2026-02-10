package com.quota.quota.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTokenDTO {
    @NotBlank(message = "Username wajib diinput")
    private String username;
    @NotBlank(message = "Password wajib diinput")
    private String password;
}
