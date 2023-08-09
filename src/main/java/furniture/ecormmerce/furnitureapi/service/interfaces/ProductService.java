package furniture.ecommerce.furnitureecommerce.service.interfaces;

import furniture.ecommerce.furnitureecommerce.data.dto.request.ProductRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.request.UpdateProductRequest;
import furniture.ecommerce.furnitureecommerce.data.dto.response.ApiResponse;
import furniture.ecommerce.furnitureecommerce.data.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
	ApiResponse createProduct(ProductRequest request);
	
	ApiResponse updateProduct(Long productId, UpdateProductRequest request);
	
	List<Product> getAllProducts();
	
	Product getProductByName(String name);
	
	Product getProductByPrice(BigDecimal price);
	
	Product saveProduct(Product product);
}
