package furniture.ecormmerce.furnitureapi.service.interfaces;


import furniture.ecormmerce.furnitureapi.data.dto.request.ProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.request.UpdateProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
	ApiResponse createProduct(ProductRequest request);
	
	ApiResponse updateProduct( UpdateProductRequest request);
	
	List<Product> getAllProducts();
	
	Product getProductByName(String name);
	
	Product getProductByPrice(BigDecimal price);
	
	Page<Product> getAllProductByPage(int pageNumber);
	
	Product saveProduct(Product product);
	Product getProductById(Long id);
	
	void deleteProductById(Long id);
	
	void deleteAllProducts();
	
	void deleteByName(String name);
}
