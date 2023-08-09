package furniture.ecommerce.furnitureecommerce.data.repository;

import furniture.ecommerce.furnitureecommerce.data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
//	List<Cart> findCartByProducts(List<Product> products);
}
