package shop.ayotl.backend.service.cart;

import shop.ayotl.backend.dto.cart.CartOutputDto;

public interface CartService {
    CartOutputDto findById(Long id);
    CartOutputDto findByUserId(Long userId);
    CartOutputDto create(Long userId);


}
