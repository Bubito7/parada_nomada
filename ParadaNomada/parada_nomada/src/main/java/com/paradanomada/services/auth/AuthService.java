package com.paradanomada.services.auth;

import com.paradanomada.dto.SignupRequest;
import com.paradanomada.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService{

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
