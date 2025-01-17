package doit.shop.service;

import doit.shop.repository.User;
import doit.shop.repository.UserRepository;
import doit.shop.controller.user.dto.UserInfoResponse;
import doit.shop.controller.user.dto.UserLoginRequest;
import doit.shop.controller.user.dto.UserLoginResponse;
import doit.shop.controller.user.dto.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void checkDuplicatedId(String userLoginId){
        User user = userRepository.findByUserLoginId(userLoginId);
        if(user != null){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }

    public UserLoginResponse signUp(UserSignUpRequest userSignUpRequest){
        if(userRepository.findByUserLoginId(userSignUpRequest.userLoginId()) != null){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User user = User.from(userSignUpRequest);

        User savedUser = userRepository.save(user);
        return UserLoginResponse.from(savedUser);
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest){
        User user = userRepository.findByUserLoginIdAndUserPassword(userLoginRequest.userLoginId(), userLoginRequest.userPassword());
        if(user == null){
            throw new IllegalArgumentException("일치하는 회원이 없습니다.");
        }

        return UserLoginResponse.from(user);
    }

    public UserInfoResponse getUserInfo(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new IllegalArgumentException("일치하는 회원이 없습니다.");
        }

        return UserInfoResponse.from(user.get());
    }
}
