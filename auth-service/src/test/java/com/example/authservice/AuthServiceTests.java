package com.example.authservice;


import com.example.authservice.authentication.Authentication;
import com.example.authservice.authentication.AuthenticationRepository;
import com.example.authservice.authentication.AuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTests {

    @InjectMocks
    private AuthenticationService authService;

    @Mock
    private AuthenticationRepository authRepository;

    private Authentication initAuth;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Before
    public void init(){
        initAuth = new Authentication();
        initAuth.setId(44);
        initAuth.setUsername("username");
        initAuth.setPassword("password");
        initAuth.setRole(Authentication.Role.USER);

    }

    @Test
    public void createAuthTest () {
        authService.createAuthentication(initAuth);
    }

    @Test
    public void loadUserTest () {
        initAuth.setPassword(encoder.encode("password"));
        when(authRepository.findByUsername("username")).thenReturn(initAuth);

        UserDetails userDetails = authService.loadUserByUsername("username");

        assertEquals(String.valueOf(44), userDetails.getUsername());
    }
}
