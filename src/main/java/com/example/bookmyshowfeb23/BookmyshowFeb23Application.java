package com.example.bookmyshowfeb23;

import com.example.bookmyshowfeb23.controllers.UserController;
import com.example.bookmyshowfeb23.dtos.SignUpRequestDTO;
import com.example.bookmyshowfeb23.dtos.SignUpResponseDTO;
import com.example.bookmyshowfeb23.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookmyshowFeb23Application implements CommandLineRunner {
    @Autowired
    private UserController userController;
    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setEmail("mohit.sharma1@scaler.com");
        signUpRequestDTO.setPassword("password");
        SignUpResponseDTO response =  userController.signUp(signUpRequestDTO);
    }

    public static void main(String[] args) {

        SpringApplication.run(BookmyshowFeb23Application.class, args);
    }

}

