package furniture.ecommerce.furnitureecommerce.data.repository;

import furniture.ecommerce.furnitureecommerce.data.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
