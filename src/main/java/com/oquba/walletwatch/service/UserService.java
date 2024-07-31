package com.oquba.walletwatch.service;

import com.oquba.walletwatch.dto.RegistrationDto;
import com.oquba.walletwatch.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
