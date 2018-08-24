package com.example.userservice.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User initUser;

    @Before
    public void init(){
        initUser = new User();
        initUser.setId(44);
        initUser.setName("Name Test");
        initUser.setEmail("test@gmail.com");
        initUser.setAddress("Address Test");
        initUser.setSalary(40000);
        initUser.setPhoneNumber("+66 99999999");

        when(userRepository.findById(44)).thenReturn(Optional.of(initUser));
    }

    @Test
    public void saveUserTest () {
        when(userRepository.save(initUser)).thenReturn(initUser);

        User user = userService.saveUser(initUser);

        assertEquals(Integer.valueOf(44), user.getId());
        assertEquals("Name Test", user.getName());

        assertEquals(User.MemberType.GOLD, user.getMemberType());

        String referenceCodeFirst = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String referenceCodeSecond = initUser.getPhoneNumber().substring(initUser.getPhoneNumber().length() - 4);
        Long referenceCode = Long.valueOf(referenceCodeFirst+referenceCodeSecond);
        assertEquals(referenceCode, user.getReferenceCode());
    }

    @Test
    public void updateUserTest () {
        User newUser = new User();
        newUser.setName("Name Test");
        newUser.setEmail("test@gmail.com");
        newUser.setAddress("Address Test");
        User user = userService.updateUser(44 ,initUser);

    }

    @Test
    public void getOneUserTest () {
        User user = userService.getOneUser(44);

        assertEquals(Integer.valueOf(44), user.getId());
    }

}
