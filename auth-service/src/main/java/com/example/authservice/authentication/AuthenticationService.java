package com.example.authservice.authentication;

import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationRepository authRepository;

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
        auth.setPassword(encoder.encode(auth.getPassword()));
        auth.setRole("USER");
        authRepository.save(auth);
    }

}
