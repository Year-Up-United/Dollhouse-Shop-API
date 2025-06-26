package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here

    void addProduct(int userId, int productId, int quantity);
    void updateQuantity(int userId, int productId, int quantity);
    void clearCart(int userId);

    // ✅ Clear the entire cart for a user — ignores extra params
    void clearCart(int userId, int ignoredProductId, int ignoredQuantity);

}
