package com.example.demo3_laba2.DataLoader;

import com.example.demo3_laba2.entity.User;
import com.example.demo3_laba2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("$2a$10$wUCNvmBXpuwfP.5LeiMxYe3uAPi6OAeGU/7GkgktUUtvJjVuYb9jG"); // 123456

        userRepository.save(user);

        System.out.println("User saved!");
    }
}
