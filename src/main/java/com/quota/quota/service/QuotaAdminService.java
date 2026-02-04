package com.quota.quota.service;

import com.quota.quota.dto.QuotaUpsertDTO;
import com.quota.quota.entity.Quota;
import com.quota.quota.repository.QuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class QuotaAdminService {
    @Autowired
    private QuotaRepository quotaRepo;

    @CachePut(value = "quota", key = "#request.id")
    public Quota createQuota(QuotaUpsertDTO request){
        Quota quota = new Quota();
        quota.setQuotaId(request.getQuotaId());
        quota.setQuotaName(request.getQuotaName());
        quota.setQuotaLimit(request.getQuotaLimit());
        quota.setCreatedAt(OffsetDateTime.now());

        return quotaRepo.save(quota);
    }

    // CRUD: Delete Setup Quota
    @CacheEvict(value = "quota", key = "#id")
    public void deleteQuota(String id) {
        quotaRepo.deleteById(id);
    }
}
