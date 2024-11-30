package shop.ayotl.backend.repository.role;

import shop.ayotl.backend.dto.role.RoleDto;

import java.util.List;

public interface RoleRepository {
    List<RoleDto> findAll();
    RoleDto findById(long id);
    RoleDto findByName(String name);
    RoleDto save(RoleDto role);
}
