package shop.ayotl.backend.service.cart;

import org.springframework.stereotype.Service;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.converter.cart.CartDtoConverter;
import shop.ayotl.backend.dto.cart.CartDto;
import shop.ayotl.backend.dto.cart.CartOutputDto;
import shop.ayotl.backend.repository.cart.CartRepository;

@Service
public class CartServiceImpl implements CartService {
private final CartRepository repository;
private final CartDtoConverter converter;

    public CartServiceImpl(CartRepository repository, CartDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public CartOutputDto findById(Long id) {
        final var found = repository.findById(id);
        return converter.dtoToOutputDto(found);
    }

    @Override
    public CartOutputDto findByUserId(Long userId) {
        final var found = repository.findByUserId(userId);
        return converter.dtoToOutputDto(found);
    }

    @Override
    public CartOutputDto create(Long userId) {
        try{
            final var found = repository.findByUserId(userId);
            return converter.dtoToOutputDto(found);
            } catch (NotFoundException e){}
            final var toCreate = CartDto.builder().userId(userId).build();
            final var created = repository.save(toCreate);

        return converter.dtoToOutputDto(created);
    }
}
