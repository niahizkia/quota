package com.quota.quota.validation;

import com.quota.quota.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    private AccountService service;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !service.isUsernameExist(s);
    }
}
