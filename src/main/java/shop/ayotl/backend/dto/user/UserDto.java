package shop.ayotl.backend.dto.user;

import shop.ayotl.backend.dto.role.RoleDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<RoleDto> roles;
}
