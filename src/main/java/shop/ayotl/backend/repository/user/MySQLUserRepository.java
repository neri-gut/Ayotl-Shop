package shop.ayotl.backend.repository.user;

import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.config.exception.PersistenceException;
import shop.ayotl.backend.converter.role.RoleDtoConverter;
import shop.ayotl.backend.converter.user.UserDtoConverter;
import shop.ayotl.backend.dto.user.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MySQLUserRepository  implements UserRepository {
    private static final String ENTITY_NAME = "User";

    private final JpaUserRepository jpaUserRepository;
    private final UserDtoConverter converter;
    private final RoleDtoConverter roleDtoConverter;

    public MySQLUserRepository(
            final JpaUserRepository jpaUserRepository,
            final UserDtoConverter converter,
            final RoleDtoConverter roleDtoConverter
    ) {
        this.jpaUserRepository = jpaUserRepository;
        this.converter = converter;
        this.roleDtoConverter = roleDtoConverter;
    }

    @Override
    public List<UserDto> findAll() {
        final var userModels = jpaUserRepository.findAll();

        return userModels
                .stream()
                .map(user -> {
                    final var roleDtos = user.getRoles()
                            .stream()
                            .map(roleDtoConverter::modelToDto)
                            .collect(Collectors.toSet());

                    return converter.modelToDto(user, roleDtos);
                })
                .toList();
    }

    @Override
    public UserDto findById(Long id) {
        final var found = jpaUserRepository.findById(id)
                .orElseThrow(this::userNotFoundException) ;
        final var roleDtos = found.getRoles()
                .stream()
                .map(roleDtoConverter::modelToDto)
                .collect(Collectors.toSet());

        return converter.modelToDto(found, roleDtos);
    }

    private NotFoundException userNotFoundException() {
        final String message = "Usuario no encontrado";

        return new NotFoundException(message, "");
    }

    @Override
    public UserDto findByEmail(String email) {
        final var found = jpaUserRepository.findByEmail(email)
                .orElseThrow(this::userNotFoundException);
        final var roleDtos = found.getRoles()
                .stream()
                .map(roleDtoConverter::modelToDto)
                .collect(Collectors.toSet());

        return converter.modelToDto(found, roleDtos);
    }

    @Override
    public UserDto save(UserDto request) {
        final var roles = request.getRoles()
                .stream()
                .map(roleDtoConverter::dtoToModel)
                .collect(Collectors.toSet());

        final var modelToSave = converter.dtoToModel(request, roles);

        try {
            final var saved = jpaUserRepository.save(modelToSave);

            return converter.modelToDto(saved, request.getRoles());
        } catch (Exception e) {
            throw new PersistenceException("Error al guardar el usuario", e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        findById(id);

        try {
            jpaUserRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new PersistenceException("Error eliminar el usuario", e.getMessage());
        }
    }
}
