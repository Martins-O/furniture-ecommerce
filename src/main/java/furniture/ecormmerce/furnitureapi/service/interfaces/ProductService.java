package furniture.ecormmerce.furnitureapi.service.interfaces;


import furniture.ecormmerce.furnitureapi.data.dto.request.ProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.request.UpdateProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.Product;

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
