package com.quota.quota.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuotaSummaryDTO {
    private String quotaName;
    private OffsetDateTime createdAt;
    private Integer sisaLimit;
}
