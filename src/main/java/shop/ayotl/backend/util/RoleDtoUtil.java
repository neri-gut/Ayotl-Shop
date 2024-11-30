package shop.ayotl.backend.util;

import shop.ayotl.backend.dto.role.RoleDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class RoleDtoUtil {
    private RoleDtoUtil() {}

    public static GrantedAuthority dtoToGrantedAuthority(RoleDto role) {
        return new SimpleGrantedAuthority(role.getName());
    }
}
