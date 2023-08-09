package furniture.ecormmerce.furnitureapi.data.repository;

import furniture.ecormmerce.furnitureapi.data.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
