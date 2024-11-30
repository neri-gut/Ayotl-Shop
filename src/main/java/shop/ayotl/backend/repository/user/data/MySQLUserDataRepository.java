package shop.ayotl.backend.repository.user.data;

import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.config.exception.PersistenceException;
import shop.ayotl.backend.converter.user.data.UserDataDtoConverter;
import shop.ayotl.backend.dto.user.data.UserDataDto;
import org.springframework.stereotype.Repository;

@Repository
public class MySQLUserDataRepository implements UserDataRepository {
    private static final String ENTITY_NAME = "User Data";

    private final JpaUserDataRepository jpaUserDataRepository;
    private final UserDataDtoConverter converter;

    public MySQLUserDataRepository(JpaUserDataRepository jpaUserDataRepository, UserDataDtoConverter converter) {
        this.jpaUserDataRepository = jpaUserDataRepository;
        this.converter = converter;
    }

    @Override
    public UserDataDto findByUserId(Long userId) {
        final var found = jpaUserDataRepository.findByUserId(userId)
                .orElseThrow(this::userDataNotFoundException);

        return converter.modelToDto(found);
    }

    private NotFoundException userDataNotFoundException() {
        return new NotFoundException("Datos de usuario no econtrados", "");
    }

    @Override
    public UserDataDto save(UserDataDto dto) {
        final var toSave = converter.dtoToModel(dto);

        try {
            final var saved = jpaUserDataRepository.save(toSave);

            return converter.modelToDto(saved);
        }
        catch (Exception exception) {
            throw new PersistenceException("Error al guardar los datos de usuario", exception.getMessage());
        }
    }

    @Override
    public void deleteByUserId(Long userId) {
        try {
            jpaUserDataRepository.deleteByUserId(userId);
        }
        catch (Exception exception) {
            throw new PersistenceException("Error al eliminar los datos de usuario", exception.getMessage());
        }
    }
}
