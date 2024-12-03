package shop.ayotl.backend.repository.cartProduct;

import shop.ayotl.backend.dto.cartProduct.CartProductDto;

public interface CartProductRepository {
    CartProductDto findById(Long id);
    CartProductDto findByCartId(Long cartId);
    CartProductDto findByProductId(Long productId);
    CartProductDto findByQuantity (Integer quantity);
    CartProductDto save(CartProductDto cartProductDto);
    //CartProductDto deleteById(Long id);

}

