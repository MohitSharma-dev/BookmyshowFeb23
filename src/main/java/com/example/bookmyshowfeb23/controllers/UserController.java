package com.example.bookmyshowfeb23.controllers;

import com.example.bookmyshowfeb23.dtos.ResponseStatus;
import com.example.bookmyshowfeb23.dtos.SignUpRequestDTO;
import com.example.bookmyshowfeb23.dtos.SignUpResponseDTO;
import com.example.bookmyshowfeb23.models.User;
import com.example.bookmyshowfeb23.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }
    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO){
        SignUpResponseDTO response = new SignUpResponseDTO();
        try{
            User user = userService.signUp(
                    signUpRequestDTO.getEmail(),
                    signUpRequestDTO.getPassword()
            );
            response.setUserId(user.getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception ex){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
