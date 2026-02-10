package com.quota.quota.service;

import com.quota.quota.component.ResourceNotFoundException;
import com.quota.quota.dto.ApplicationUserDetail;
import com.quota.quota.dto.RegisterAccountDTO;
import lombok.RequiredArgsConstructor;
import com.quota.quota.entity.Account;
import com.quota.quota.entity.Role;
import com.quota.quota.repository.AccountRepository;
import com.quota.quota.repository.RoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    public Boolean isUsernameExist(String username) {
        var total = accountRepo.countExistingUser(username);
        return (total > 0);
    }

    public void register(RegisterAccountDTO dto) {
        var entity = new Account();
        entity.setUsername(dto.getUsername());
        var hashPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashPassword);

        // get the role id by rolename
        Role userRole = roleRepo.findByRoleName(dto.getRoleName())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        entity.setRoleId(userRole.getId());
        accountRepo.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new ApplicationUserDetail(account);
    }

    public String getRole(String username) {
        return accountRepo.findByUsername(username)
                .map(Account::getRoleId)
                .map(UUID::toString)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found for user: " + username));
    }
}
