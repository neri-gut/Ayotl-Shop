package shop.ayotl.backend.service.role;

import shop.ayotl.backend.config.exception.InvalidParamsException;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.converter.role.RoleDtoConverter;
import shop.ayotl.backend.dto.role.RoleCreateRequest;
import shop.ayotl.backend.dto.role.RoleDto;
import shop.ayotl.backend.repository.role.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleDtoConverter converter;

    public RoleServiceImpl(RoleRepository roleRepository, RoleDtoConverter converter) {
        this.roleRepository = roleRepository;
        this.converter = converter;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleDto create(RoleCreateRequest request) {
        try {
            roleRepository.findByName(request.getName());

            throw new InvalidParamsException("Ya existe un rol con el mismo nombre", "");
        }
        catch (NotFoundException exception) {
        }

        final var toSave = converter.createRequestToDto(request);

        return roleRepository.save(toSave);
    }
}
