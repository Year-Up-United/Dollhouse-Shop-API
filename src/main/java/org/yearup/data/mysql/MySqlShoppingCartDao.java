package org.yearup.data.mysql;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Component // spring bean
public class MySqlShoppingCartDao implements ShoppingCartDao {
    private final DataSource dataSource;
    private final ProductDao productDao;
    public MySqlShoppingCartDao(DataSource dataSource, ProductDao productDao) {
        this.dataSource = dataSource;
        this.productDao = productDao;
    }

    // retrieve the full cart for a given user
    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart cart = new ShoppingCart();
        Map<Integer, ShoppingCartItem> items = new HashMap<>();
        String sql = "SELECT product_id, quantity FROM shopping_cart WHERE user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Product product = productDao.getById(productId);

                ShoppingCartItem item = new ShoppingCartItem();
                item.setProduct(product);
                item.setQuantity(quantity);
                items.put(productId, item);
            }
            cart.setItems(items);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get shopping cart.", e);
        }
        return cart;
    }
    // add product to the cart or increase its quantity
    @Override
    public void addProduct(int userId, int productId, int quantity) {
        String sql = """
           INSERT INTO shopping_cart (user_id, product_id, quantity)
           VALUES (?, ?, ?)
           ON DUPLICATE KEY UPDATE quantity = quantity + ?;
       """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add product to cart.", e);
        }
    }
    // update the quantity of a specific product in the cart
    @Override
    public void updateQuantity(int userId, int productId, int quantity) {
        String sql = """
           UPDATE shopping_cart
           SET quantity = ?
           WHERE user_id = ? AND product_id = ?;
       """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, userId);
            stmt.setInt(3, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update cart quantity.", e);
        }
    }

    @Override
    public void clearCart(int userId) {

    }

    // ✅ Clear the entire cart for a user — ignores extra params
    @Override
    public void clearCart(int userId, int ignoredProductId, int ignoredQuantity) {
        String sql = "DELETE FROM shopping_cart WHERE user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to clear cart.", e);
        }
    }
}