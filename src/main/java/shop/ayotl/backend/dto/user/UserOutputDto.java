package shop.ayotl.backend.dto.user;

import shop.ayotl.backend.dto.role.RoleDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserOutputDto {
    private Long id;
    private String email;
    private String createdAt;
    private String updatedAt;
    private Set<RoleDto> roles;
}
