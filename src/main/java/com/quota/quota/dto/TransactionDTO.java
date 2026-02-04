package com.quota.quota.dto;

import com.quota.quota.constant.QuotaStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @Size(max=20, message="Transaction Id can't be no more than 20 characters.")
    @NotBlank(message = "transaction can not be empty.")
    private String tranId;

    @Size(max=50, message="Quota Id can't be no more than 20 characters.")
    @NotBlank(message = "Quota Id can not be empty.")
    private String quotaId;

    @NotBlank(message = "Taken By cannot be empty.")
    private String takenBy;
}
