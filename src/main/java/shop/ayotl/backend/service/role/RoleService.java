package shop.ayotl.backend.service.role;

import shop.ayotl.backend.dto.role.RoleCreateRequest;
import shop.ayotl.backend.dto.role.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();
    RoleDto create(RoleCreateRequest request);
}
