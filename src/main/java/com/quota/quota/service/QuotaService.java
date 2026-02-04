package com.quota.quota.service;

import com.quota.quota.dto.QuotaSummaryDTO;
import com.quota.quota.dto.TransactionDetailDTO;
import com.quota.quota.entity.Quota;
import com.quota.quota.repository.QuotaRepository;
import com.quota.quota.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.List;

@Service
public class QuotaService {
    @Autowired
    private QuotaRepository quotaRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    public QuotaSummaryDTO getQuotaDetails(String idQuota) {
        Quota quota = quotaRepository.findById(idQuota)
                .orElseThrow(() -> new RuntimeException("Data Not Found"));
        int sisaQuota = getAvailableQuota(idQuota);
        return new QuotaSummaryDTO(
                quota.getQuotaName(),
                quota.getCreatedAt(),
                sisaQuota
        );
    }

    public Integer getAvailableQuota(String idQuota){
        Quota quota = quotaRepository.findById(idQuota)
                .orElseThrow(() -> new RuntimeException("Data Not Found"));
        int totalReservedQuota = quotaRepository.getTotalReservedQuota(idQuota);
        return quota.getQuotaLimit() - totalReservedQuota;
    }

    public Object getAllTranByQuotaId(String quotaId){
        var rows = transactionRepository.getTranByQuotaId(quotaId);
        return rows;
    }

}
