package com.quota.quota.dto;

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
public class TransactionCreateDTO {

    @Size(max=36, message="User Id can't be no more than 36 characters.")
    @NotBlank(message = "transaction can not be empty.")
    private UUID userId;

    @Size(max=36, message="Quota Id can't be no more than 36 characters.")
    @NotBlank(message = "Quota Id can not be empty.")
    private UUID quotaId;

}
