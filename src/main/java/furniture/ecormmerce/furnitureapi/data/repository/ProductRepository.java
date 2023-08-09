package furniture.ecommerce.furnitureecommerce.data.repository;

import furniture.ecommerce.furnitureecommerce.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findProductByName(String name);
	
	Product findProductByPrice(BigDecimal price);
	
	Product findProductById(Long productId);
	
}
