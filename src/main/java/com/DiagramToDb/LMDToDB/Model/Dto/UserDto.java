package com.DiagramToDb.LMDToDB.Model.Dto;

import com.DiagramToDb.LMDToDB.Model.Entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotNull(message ="username is required")
    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{4,20}$)(?!.*[_.]{2})[^_.].*[^_.]$", message="Invalid Username Given")
    private String username;
    @NotNull(message ="Passowrd is required")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message="Password Must Contains:\n" +
            "At least one upper case English letter,\n" +
            "At least one lower case English letter, \n" +
            "At least one digit, \n" +
            "At least one special character,\n" +
            "Minimum eight in length")
    private String password;
    @NotNull(message ="Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message="Invalid Username Given")

    private String email;
    public static UserDto toDto(UserEntity u){
        return UserDto.builder()
                .id(u.getId())
                .username(u.getUsername())
                .password(u.getPassword())
                .email(u.getEmail())
                .build();

    }
}
