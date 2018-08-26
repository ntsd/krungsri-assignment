package com.example.authservice.authentication;

import java.util.List;

import com.example.authservice.authentication.exception.AuthenticationIdAlreadyExistException;
import com.example.authservice.authentication.exception.AuthenticationUsernameAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService  {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final AuthenticationRepository authRepository;

    @Autowired
    public AuthenticationService(AuthenticationRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        try {
            Authentication auth = authRepository.findByUsername(username);
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_" + auth.getRole());
            return new User(String.valueOf(auth.getId()), auth.getPassword(), grantedAuthorities);
        }
        catch (Exception e){
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
    }

    public void createAuthentication(Authentication auth) {
        if (!authRepository.existsById(auth.getId())) {
            try {
                auth.setPassword(encoder.encode(auth.getPassword()));
                // auth.setRole(Authentication.Role.USER);
                authRepository.save(auth);
            }
            catch (Exception e) {
                throw new AuthenticationUsernameAlreadyExistException("Authentication Username: " + auth.getUsername() + " already exist");
            }
        }
        else {
            throw new AuthenticationIdAlreadyExistException("Authentication ID: " + auth.getId() + " already exist");
        }
    }
}
