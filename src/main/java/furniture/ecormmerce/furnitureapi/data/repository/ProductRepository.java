package furniture.ecormmerce.furnitureapi.data.repository;

import furniture.ecormmerce.furnitureapi.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findProductByName(String name);
	
	Product findProductByPrice(BigDecimal price);
	
	Product findProductById(Long productId);
	
	void deleteProductById(Long id);
	
	void deleteByName(String name);
}
