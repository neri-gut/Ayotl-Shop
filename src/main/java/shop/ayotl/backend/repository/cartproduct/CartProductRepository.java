package shop.ayotl.backend.repository.cartproduct;

import shop.ayotl.backend.dto.cartproduct.CartProductDto;

public interface CartProductRepository {
    CartProductDto findByCartIdAndProductId(Long cartId, Long productId);
    CartProductDto save(CartProductDto dto);
}
