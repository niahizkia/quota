package com.quota.quota.service;

import com.quota.quota.constant.QuotaStatus;
import com.quota.quota.dto.*;
import com.quota.quota.entity.Account;
import com.quota.quota.entity.Quota;
import com.quota.quota.entity.Transaction;
import com.quota.quota.repository.AccountRepository;
import com.quota.quota.repository.QuotaRepository;
import com.quota.quota.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private QuotaRepository quotaRepo;

    public UserTransactionDTO getUserTranDetails(UUID aacountId) {

        Account account = accountRepo.findById(aacountId)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));
        List<TransactionListDTO> transactions = transactionRepo.getTranByAccountId(account.getId());
        if (transactions.isEmpty()) {
            throw new RuntimeException("No transactions found for this account");
        }
        UserInformationDTO userInfo = new UserInformationDTO(account.getId(), account.getUsername());
        return new UserTransactionDTO(
                userInfo, transactions
        );
    }
    @CachePut(value = "transaction", key = "#request.id")
    public Transaction createTransaction(TransactionCreateDTO request){
//        int totalReservedQuota = quotaRepository.getTotalReservedQuota(request.getQuotaId());
        Quota quota = quotaRepo.findById(request.getQuotaId())
                .orElseThrow(() -> new RuntimeException("Quota Not Found"));
        Account account = accountRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Account Not Found"));
        Transaction trans = new Transaction(
                quota.getId(),
                account.getId(),
                QuotaStatus.reserved,
                OffsetDateTime.now());
        return transactionRepo.save(trans);
    }

    // CRUD: Delete Setup Quota
//    @CacheEvict(value = "transaction", key = "#id")
//    public void deleteTransaction(String id) {
//        transactionRepo.deleteById(id);
//    }
}
