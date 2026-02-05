package com.quota.quota.service;

import com.quota.quota.dto.ApplicationUserDetail;
import com.quota.quota.dto.RegisterAccountDTO;
import com.quota.quota.entity.Account;
import com.quota.quota.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean isUsernameExist(String username){
        var total = accountRepo.countExistingUser(username);
        return (total > 0);
    }

    public void register(RegisterAccountDTO dto){
        var entity = new Account();
        entity.setUsername(dto.getUsername());
        var hashPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashPassword);
        entity.setRoleId(dto.getRoleId());
        accountRepo.save(entity);
    }
//
//    public void changePassword(ChangePasswordDTO dto){
//        var entity = accountRepository.findById(dto.getUsername()).get();
//        var hashedNewPassword = passwordEncoder.encode(dto.getPassword());
//        entity.setPassword(hashedNewPassword);
//        accountRepository.save(entity);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new ApplicationUserDetail(account);
    }

    public Boolean checkUsernamePassword(String username, String password){
        return accountRepo.findById(username)
                .map(entity -> passwordEncoder.matches(password, entity.getPassword()))
                .orElse(false); // Returns false if user not found
    }

    public String getRole(String username){
        return accountRepo.findById(username)
                .map(Account::getRoleId)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found for user: " + username));
    }
}
