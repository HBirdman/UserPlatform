package aston.homework.mapper;

import aston.homework.dto.UserRequestDTO;
import aston.homework.dto.UserResponseDTO;
import aston.homework.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User map(UserRequestDTO dto) {
        if (dto == null) return null;
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public UserResponseDTO map(User user) {
        if (user == null) return null;
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
