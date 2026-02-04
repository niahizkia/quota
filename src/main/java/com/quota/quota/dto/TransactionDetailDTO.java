package com.quota.quota.dto;

import com.quota.quota.constant.QuotaStatus;
import com.quota.quota.entity.Quota;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailDTO {
    private String tranId;
    private String quotaId;
    private QuotaStatus status;
    private String takenBy;
    private OffsetDateTime createdAt;

}
