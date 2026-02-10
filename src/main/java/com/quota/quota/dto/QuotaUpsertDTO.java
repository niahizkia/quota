package com.quota.quota.dto;

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
public class QuotaUpsertDTO {

    @Size(max=50, message="Quota Name can't be no more than 50 characters.")
    @NotBlank(message = "Quota Id can not be empty.")
    private String quotaName;

    @NotNull(message = "Quota Limit can't be empty or null.")
    private Integer quotaLimit;

    private OffsetDateTime createdAt;
}
