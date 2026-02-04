package com.quota.quota.service;

import com.quota.quota.constant.QuotaStatus;
import com.quota.quota.dto.QuotaSummaryDTO;
import com.quota.quota.dto.QuotaUpsertDTO;
import com.quota.quota.dto.TransactionDTO;
import com.quota.quota.dto.TransactionDetailDTO;
import com.quota.quota.entity.Quota;
import com.quota.quota.entity.Transaction;
import com.quota.quota.repository.QuotaRepository;
import com.quota.quota.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepo;
//    @Autowired
//    private QuotaRepository quotaRepository;

    public TransactionDetailDTO getTranDetails(String idTransaction) {
        Transaction transaction = transactionRepo.findById(idTransaction)
                .orElseThrow(() -> new RuntimeException("Data Not Found"));
        return new TransactionDetailDTO(
                transaction.getTranId(),
                transaction.getQuotaId(),
                transaction.getStatus(),
                transaction.getTakenBy(),
                transaction.getCreatedAt()
        );
    }
    @CachePut(value = "transaction", key = "#request.id")
    public Transaction createTransaction(TransactionDTO request){
//        int totalReservedQuota = quotaRepository.getTotalReservedQuota(request.getQuotaId());
        Transaction trans = new Transaction();

        trans.setTranId(request.getTranId());
        trans.setQuotaId(request.getQuotaId());
        trans.setStatus(QuotaStatus.reserved);
        trans.setTakenBy(request.getTakenBy());
        trans.setCreatedAt(OffsetDateTime.now());
        return transactionRepo.save(trans);
    }

    // CRUD: Delete Setup Quota
    @CacheEvict(value = "transaction", key = "#id")
    public void deleteTransaction(String id) {
        transactionRepo.deleteById(id);
    }
}
