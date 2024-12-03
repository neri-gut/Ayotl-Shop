package shop.ayotl.backend.repository.cart;

import org.springframework.stereotype.Repository;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.config.exception.PersistenceException;
import shop.ayotl.backend.converter.cart.CartDtoConverter;
import shop.ayotl.backend.dto.cart.CartDto;
import shop.ayotl.backend.repository.user.JpaUserRepository;

@Repository
public class MySQLCartRepository implements CartRepository {
   private final JpaCartRepository jpaCartRepository;
   private final CartDtoConverter converter;
   private final JpaUserRepository jpaUserRepository;

    public MySQLCartRepository(JpaCartRepository jpaCartRepository, CartDtoConverter converter, JpaUserRepository jpaUserRepository) {
        this.jpaCartRepository = jpaCartRepository;
        this.converter = converter;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public CartDto findById(Long id) {
        final var found= jpaCartRepository.findById(id).orElseThrow(()-> new NotFoundException("carrito no encontrado", ""));

        return converter.modelToDto(found);
    }

    @Override
    public CartDto findByUserId(Long userId) {
        final var found= jpaCartRepository.findByUser_id(userId).orElseThrow(()-> new NotFoundException("carrito no encontrado", ""));

        return converter.modelToDto(found);
    }

    @Override
    public CartDto save(CartDto dto) {
        final var user = jpaUserRepository.findById(dto.getUserId()).orElseThrow(()-> new NotFoundException("Usuario no encontrado", "")) ;
        final var toSave= converter.dtoToModel(dto, user);

        try {
            final var saved = jpaCartRepository.save(toSave);

            return converter.modelToDto(saved);
        }
        catch (Exception e){
            throw new PersistenceException("error al guardar carrito", e.getMessage());
        }



    }


}
