package shop.ayotl.backend.converter.role;

import shop.ayotl.backend.dto.role.RoleCreateRequest;
import shop.ayotl.backend.dto.role.RoleDto;
import shop.ayotl.backend.model.Role;

public class RoleDtoConverter {
    public RoleDto modelToDto(final Role role) {
        return RoleDto
                .builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .build();
    }

    public Role dtoToModel(final RoleDto dto) {
        return Role
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public RoleDto createRequestToDto(RoleCreateRequest request) {
        return RoleDto
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}
