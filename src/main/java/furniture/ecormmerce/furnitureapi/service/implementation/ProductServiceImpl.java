package furniture.ecormmerce.furnitureapi.service.implementation;

import furniture.ecormmerce.furnitureapi.cloud.CloudService;
import furniture.ecormmerce.furnitureapi.data.dto.request.ProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.request.UpdateProductRequest;
import furniture.ecormmerce.furnitureapi.data.dto.response.ApiResponse;
import furniture.ecormmerce.furnitureapi.data.model.Product;
import furniture.ecormmerce.furnitureapi.data.repository.ProductRepository;
import furniture.ecormmerce.furnitureapi.service.interfaces.ProductService;
import furniture.ecormmerce.furnitureapi.utils.Responses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository repository;
	private final CloudService service;
	@Override
	public ApiResponse createProduct(ProductRequest request) {
		var imageUrl = service.upload (request.getPictures ());
		Product addProduct = Product
				.builder()
				.price (request.getPrice())
				.description(request.getDescription())
				.colorType (request.getColorType())
				.sizeType (request.getSizeType())
				.name (request.getName())
				.quantity (request.getQuantity())
				.pictures (imageUrl)
				.productRating (request.getRateProduct ())
				.build();
		var saved = repository.save(addProduct);
		return Responses.createdResponse (saved);
	}
	
	@Override
	public ApiResponse updateProduct(Long productId, UpdateProductRequest request){
		Optional<Product> checkProduct = repository.findById (productId);
		if (checkProduct.isEmpty ()) {
			throw new IllegalStateException("Product not found");
		}
		Product updateProduct = checkProduct.get();
		updateProduct.setColorType (request.getColorType());
		updateProduct.setName (request.getName());
		updateProduct.setDescription (request.getDescription());
		updateProduct.setPrice (request.getPrice().abs ());
		updateProduct.setSizeType (request.getSizeType());
		updateProduct.setQuantity (request.getQuantity());
		var savedProduct = repository.save(updateProduct);
		return Responses.okResponse (savedProduct);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return repository.findAll ();
	}
	
	@Override
	public Product getProductByName (String name) {
		return repository.findProductByName (name);
	}
	
	@Override
	public Product getProductByPrice(BigDecimal price) {
		return repository.findProductByPrice(price);
	}
	
	@Override
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
}
