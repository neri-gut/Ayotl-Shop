package shop.ayotl.backend.converter.cart;

import shop.ayotl.backend.common.constant.DatePattern;
import shop.ayotl.backend.converter.date.DateConverter;
import shop.ayotl.backend.dto.cart.CartDto;
import shop.ayotl.backend.dto.cart.CartOutputDto;
import shop.ayotl.backend.model.Cart;
import shop.ayotl.backend.model.User;

public class CartDtoConverter {
    public CartDto modelToDto(Cart model){
    return CartDto.builder()
            .id(model.getId())
            .userId(model.getUser().getId())
            .createdAt(model.getCreatedAt())
            .build();
    }

public Cart dtoToModel(CartDto dto, User user){

      return Cart.builder().id(dto.getId()).user(user).createdAt(dto.getCreatedAt()).build();

    }

    public CartOutputDto dtoToOutputDto(CartDto dto){
        return CartOutputDto.builder().id(dto.getId()).userId(dto.getUserId()).createdAt(DateConverter.temporalToString(dto.getCreatedAt(), DatePattern.DD_MM_YYYY)).build();

    }
}
