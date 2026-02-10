package com.quota.quota.dto;

import com.quota.quota.constant.QuotaStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionListDTO {
    private UUID tranId;
    private UUID quotaId;
    private String quotaName;
    private QuotaStatus status;
    private OffsetDateTime createdAt;
}
