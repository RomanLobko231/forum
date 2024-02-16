package com.roman.forum;

import com.roman.forum.model.Message;
import com.roman.forum.model.Role;
import com.roman.forum.model.Topic;
import com.roman.forum.repository.RolesRepository;
import com.roman.forum.repository.TopicRepository;
import com.roman.forum.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(RolesRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
//		return args -> {
//			userRepository.deleteAll();
//		};
//	}
}
