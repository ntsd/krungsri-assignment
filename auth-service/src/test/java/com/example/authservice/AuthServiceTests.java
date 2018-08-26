package com.example.authservice;


import com.example.authservice.authentication.Authentication;
import com.example.authservice.authentication.AuthenticationRepository;
import com.example.authservice.authentication.AuthenticationService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTests {

    @InjectMocks
    private AuthenticationService authService;

    @Mock
    private AuthenticationRepository authRepository;

    private Authentication initAuth;

    @Before
    public void init(){
        initAuth = new Authentication();
        initAuth.setId(44);
        initAuth.setUsername("username");
        initAuth.setPassword("password");
        initAuth.setRole("USER");

        when(authRepository.findByUsername("username")).thenReturn(initAuth);
    }
}
