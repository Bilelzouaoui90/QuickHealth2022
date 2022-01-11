package com.corp.cabinet.services;

import com.corp.cabinet.controlleur.dto.UserRegistrationDto;
import com.corp.cabinet.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
