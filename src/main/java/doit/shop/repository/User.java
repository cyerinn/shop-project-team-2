package doit.shop.repository;


import doit.shop.controller.user.dto.UserSignUpRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String userLoginId;
    private String userPassword;
    private String userNickName;
    private String userPhoneNumber;

    @Builder
    public User(String userName, String userLoginId, String userPassword, String userNickName, String userPhoneNumber){
        this.userName = userName;
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userNickName = userNickName;
        this.userPhoneNumber = userPhoneNumber;
    }

    public static User from (UserSignUpRequest userSignUpRequest){
        User user = User.builder()
                .userName(userSignUpRequest.userName())
                .userLoginId(userSignUpRequest.userLoginId())
                .userPassword(userSignUpRequest.userPassword())
                .userNickName(userSignUpRequest.userNickName())
                .userPhoneNumber(userSignUpRequest.userPhoneNumber())
                .build();
    }
}

