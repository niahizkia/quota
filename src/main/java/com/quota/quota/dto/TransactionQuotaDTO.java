package com.quota.quota.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionQuotaDTO {
    private QuotaSummaryDTO quota;
    private Object transactions;
}
