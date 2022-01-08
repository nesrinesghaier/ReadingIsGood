package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.User;
import com.getir.readingisgood.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public void registerCustomer() {
        User user = User.builder()
                .username("test")
                .firstName("test").lastName("test").address("test").build();
        userRepository.save(user);
    }
}
