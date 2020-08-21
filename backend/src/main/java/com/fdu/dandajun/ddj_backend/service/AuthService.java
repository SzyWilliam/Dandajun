package com.fdu.dandajun.ddj_backend.service;

import fudan.se.lab2.controller.request.LoginRequest;
import fudan.se.lab2.controller.request.RegisterRequest;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.UserRepository;
import fudan.se.lab2.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Yu Zhexuan
 */
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String login(LoginRequest loginRequest) throws UsernameNotFoundException {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User: '" + username + "' not found.");
        } else {
            String correctPassword = user.getPassword();
            if (password.equals(correctPassword)) {
                return jwtTokenUtil.generateToken(user);
            } else {
                return "密码错误！";
            }
        }
    }

    public String register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String name = registerRequest.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User(username, password, name);
            userRepository.save(user);
            return "success";
        } else {
            return "error";
        }
    }
}
