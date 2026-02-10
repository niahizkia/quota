package com.quota.quota.service;

import com.quota.quota.component.ResourceNotFoundException;
import com.quota.quota.dto.QuotaSummaryDTO;
import com.quota.quota.entity.Quota;
import com.quota.quota.repository.QuotaRepository;
import com.quota.quota.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuotaService {

    private final QuotaRepository quotaRepository;
    private final TransactionRepository transactionRepository;

    public QuotaSummaryDTO getQuotaDetails(UUID quotaId) {
        Quota quota = quotaRepository.findById(quotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Data Not Found"));
        int sisaQuota = getAvailableQuota(quotaId);
        return new QuotaSummaryDTO(
                quota.getQuotaName(),
                quota.getCreatedAt(),
                sisaQuota);
    }

    public Integer getAvailableQuota(UUID quotaId) {

        Quota quota = quotaRepository.findById(quotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Data Not Found"));
        int totalReservedQuota = quotaRepository.getTotalReservedQuota(quota.getId());
        return quota.getMaxLimit() - totalReservedQuota;
    }

    public Object getAllTranByQuotaId(UUID quotaId) {
        Quota quota = quotaRepository.findById(quotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Data Not Found"));
        var rows = transactionRepository.getTranByQuotaId(quotaId);
        return rows;
    }

}
