package shop.ayotl.backend.converter.user;

import shop.ayotl.backend.common.constant.DatePattern;
import shop.ayotl.backend.converter.date.DateConverter;
import shop.ayotl.backend.dto.role.RoleDto;
import shop.ayotl.backend.dto.user.UserCreateRequest;
import shop.ayotl.backend.dto.user.UserDto;
import shop.ayotl.backend.dto.user.UserOutputDto;
import shop.ayotl.backend.model.Role;
import shop.ayotl.backend.model.User;

import java.util.Set;

public class UserDtoConverter {
    private static final String DATE_TIME_PATTERN = DatePattern.DD_MM_YYYY_HH_MM_SS;

    public UserDto modelToDto(final User user, final Set<RoleDto> roles) {
        return UserDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .roles(roles)
                .build();
    }

    public User dtoToModel(final UserDto dto, final Set<Role> roles) {
        return User
                .builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .roles(roles)
                .build();
    }

    public UserDto createRequestDtoToDto(final UserCreateRequest createRequest, final RoleDto roleDto) {
        return UserDto
                .builder()
                .email(createRequest.getEmail())
                .password(createRequest.getPassword())
                .roles(Set.of(roleDto))
                .build();
    }

    public UserOutputDto dtoToOutputDto(final UserDto dto) {
        return UserOutputDto
                .builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .createdAt(DateConverter.temporalToString(dto.getCreatedAt(), DATE_TIME_PATTERN))
                .updatedAt(DateConverter.temporalToString(dto.getUpdatedAt(), DATE_TIME_PATTERN))
                .roles(dto.getRoles())
                .build();
    }
}
