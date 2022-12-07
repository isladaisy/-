package com.example.hanghaeboardupgrade.service;

import com.example.hanghaeboardupgrade.dto.LoginRequestDto;
import com.example.hanghaeboardupgrade.dto.ResponseDto;
import com.example.hanghaeboardupgrade.dto.SignupRequestDto;
import com.example.hanghaeboardupgrade.entity.User;
import com.example.hanghaeboardupgrade.jwt.JwtUtil;
import com.example.hanghaeboardupgrade.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자");
        }

        User user = new User(username, password);
        userRepository.save(user);
        return new ResponseDto(200, "회원가입 성공");
    }


    @Transactional
    public ResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(jwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));

        return new ResponseDto(200, "로그인 성공");
    }

}
