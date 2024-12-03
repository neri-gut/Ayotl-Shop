package shop.ayotl.backend.repository.cart;

import shop.ayotl.backend.dto.cart.CartDto;

public interface CartRepository {
    CartDto findById(Long id);
    CartDto findByUserId(Long userId);
    CartDto save(CartDto dto);
}
