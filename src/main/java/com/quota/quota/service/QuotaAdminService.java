package com.quota.quota.service;

import com.quota.quota.dto.QuotaUpsertDTO;
import com.quota.quota.entity.Quota;
import com.quota.quota.repository.QuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class QuotaAdminService {
    @Autowired
    private QuotaRepository quotaRepo;

    @CachePut(value = "quota", key = "#request.id")
    public Quota createQuota(QuotaUpsertDTO request){
        Quota quota = new Quota();
        quota.setQuotaName(request.getQuotaName());
        quota.setMaxLimit(request.getQuotaLimit());
        quota.setCreatedAt(OffsetDateTime.now());

        return quotaRepo.save(quota);
    }

    // CRUD: Delete Setup Quota
    @CacheEvict(value = "quota", key = "#id")
    public void deleteQuota(UUID id) {
        quotaRepo.deleteById(id);
    }
}
