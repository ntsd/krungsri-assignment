package com.example.userservice.user;

import com.example.userservice.user.exception.UserNotFoundException;
import com.example.userservice.user.exception.UserSalaryTooLowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    private DateFormat referenceFormat = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Long getReferenceCode (Date currentDate, String phoneNumber) {
        return Long.valueOf(referenceFormat.format(currentDate)+phoneNumber.substring(phoneNumber.length() - 4));
    }

    private User.MemberType getMemberType(Integer salary) {
        if (salary < 30000) {
            return User.MemberType.SILVER;
        }
        else if (salary <= 50000) {
            return User.MemberType.GOLD;
        }
        return User.MemberType.PLATINUM;
    }

    User saveUser (User user) {
        if (user.getSalary() < 15000) {
            throw new UserSalaryTooLowException(String.format("Salary should not less than 15000 get %d", user.getSalary()));
        }
        user.setMemberType(getMemberType(user.getSalary()));

        user.setPhoneNumber(user.getPhoneNumber().trim());

        Date currentDate = new Date();

        user.setReferenceCode(getReferenceCode(currentDate, user.getPhoneNumber()));

        return userRepository.save(user);
    }

    User getOneUser (Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("User not found by Id: " + id));
    }

    User updateUser (Integer id, User newUser) {
        newUser.setId(id);
        if (newUser.getSalary() != null) {
            if (newUser.getSalary() < 15000) {
                throw new UserSalaryTooLowException(String.format("Salary should not less than 15000 get %d", newUser.getSalary()));
            }
            newUser.setMemberType(getMemberType(newUser.getSalary()));
        }
        return userRepository.save(newUser);
    }

    void deleteUser (Integer id) {
        try {
            userRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new UserNotFoundException("Can't delete User not found by Id: " + id);
        }
    }
}
